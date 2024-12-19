package di.fa.kagateway.core.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import di.fa.kacommon.response.ResponseStatus;
import di.fa.kagateway.core.annotates.UnAuthController;
import di.fa.kagateway.core.annotates.UnAuthMethod;
import di.fa.kagateway.core.exception.KAGatewayException;
import di.fa.kagateway.core.security.CredentialsHolder;
import di.fa.kagateway.feign.client.KeycloakFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AspectValidate {

    //    final BreadCrumbService breadCrumbService;
    final CredentialsHolder credentialsHolder;
    final KeycloakFeignClient keycloakFeignClient;

    @Value("${keycloak.realm}")
    String keycloakRealm;

    @Value("${ignore.token-validation}")
    boolean ignoreTokenValidate;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void controllerPointcut() {
        // Do nothing
    }

    @Around("controllerPointcut()")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Object> controllerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        var signature = (MethodSignature) joinPoint.getSignature();
        var classType = joinPoint.getTarget().getClass();
        var method = classType.getMethod(signature.getMethod().getName(), signature.getMethod().getParameterTypes());
        var ignoreController = classType.getAnnotation(UnAuthController.class);
        var ignoreMethod = method.getAnnotation(UnAuthMethod.class);
        var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        credentialsHolder.setRefreshToken(request.getHeader("ka-refresh-token"));

        if (!(ignoreController != null || ignoreMethod != null) && StringUtils.isBlank(request.getHeader("Authorization"))) {
            throw new KAGatewayException(HttpStatus.UNAUTHORIZED, ResponseStatus.UNAUTHORIZED);
        }

        if (!(ignoreController != null || ignoreMethod != null)) {
            var token = request.getHeader("Authorization");

            if (!ignoreTokenValidate) this.validateAccessToken(token);

            credentialsHolder.setAccessToken(token);
            credentialsHolder.setUserInfo(parseJWT(token));
        }

        return this.response((ResponseEntity<Object>) joinPoint.proceed());
    }

    private void validateAccessToken(String token) {
        try {
            keycloakFeignClient.getKeycloakUserInfo(keycloakRealm, token);
        } catch (Exception ex) {
            log.error(String.format("Get keycloak user info fail: %s", ex));
            throw new KAGatewayException(HttpStatus.UNAUTHORIZED, ResponseStatus.UNAUTHORIZED);
        }
    }

    private ResponseEntity<Object> response(ResponseEntity<Object> response) {
//        var headers = breadCrumbService.getTracingHeader();
        var headers = new HttpHeaders();
        headers.set("file-name", response.getHeaders().getFirst("file-name"));

        return ResponseEntity.status(response.getStatusCode()).headers(headers).body(response.getBody());
    }

    public static JsonNode parseJWT(String token) throws JsonProcessingException {
        token = token.replace("Bearer ", "");

        var splitString = token.split("\\.");
        var encodeBody = splitString[1];
        var base64 = new org.apache.commons.codec.binary.Base64(true);
        var body = new String(base64.decode(encodeBody));

        return new ObjectMapper().readValue(body, JsonNode.class);
    }
}
