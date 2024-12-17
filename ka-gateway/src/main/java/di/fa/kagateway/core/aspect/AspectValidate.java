package di.fa.kagateway.core.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import di.fa.kacommon.response.ResponseStatus;
import di.fa.kagateway.core.annotates.UnAuthController;
import di.fa.kagateway.core.annotates.UnAuthMethod;
import di.fa.kagateway.core.exception.KAGatewayException;
import di.fa.kagateway.core.security.CredentialsHolder;
import di.fa.kagateway.core.security.AESUtils;
import di.fa.kagateway.core.security.RSAUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Aspect
@Order(4)
@Component
@RequiredArgsConstructor
public class AspectValidate {

    //    final BreadCrumbService breadCrumbService;
    final CredentialsHolder credentialsHolder;

    @Value("${keycloak.realm}")
    String keycloakRealm;

    @Value("${force.encrypt.request}")
    boolean forceEncrypt;

    @Value("${ignore.token-validation}")
    boolean ignoreTokenValidate;

    @Value("${rsa.private.key}")
    String privateKey;

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
        var wrappedRequest = (ContentCachingRequestWrapper) request;

        credentialsHolder.setRefreshToken(wrappedRequest.getHeader("ka-refresh-token"));
        credentialsHolder.setSecretKey(wrappedRequest.getHeader("ka-secret-Key"));


        if (!(ignoreController != null || ignoreMethod != null) && StringUtils.isBlank(wrappedRequest.getHeader("Authorization"))) {
            throw new KAGatewayException(HttpStatus.UNAUTHORIZED, ResponseStatus.UNAUTHORIZED);
        }

        if (!(ignoreController != null || ignoreMethod != null)) {
            var token = wrappedRequest.getHeader("Authorization");

            if (!ignoreTokenValidate) this.validateAccessToken(token);

            credentialsHolder.setAccessToken(token);
            credentialsHolder.setUserInfo(parseJWT(token));
        }

        var secret = credentialsHolder.getSecretKey();
        var encrypt = new String(wrappedRequest.getContentAsByteArray());
        var isBodyMethod = Arrays.asList(HttpMethod.POST.name(), HttpMethod.PUT.name()).contains(wrappedRequest.getMethod());

        if (isBodyMethod && StringUtils.isNotBlank(encrypt)) {
            this.validateRequest(secret, encrypt);
        } else {
            if (StringUtils.isNotBlank(secret)) {
                secret = RSAUtils.decrypt(secret, privateKey);
                var secretKey = secret.substring(8, secret.length() - 8);
                var iv = secret.substring(0, 8).concat(secret.substring(secret.length() - 8));

                return this.encryptResponse((ResponseEntity<Object>) joinPoint.proceed(), secretKey, iv);
            } else {
                return this.response((ResponseEntity<Object>) joinPoint.proceed());
            }
        }

        var args = joinPoint.getArgs();

        if (!forceEncrypt && StringUtils.isBlank(secret)) {
            return this.response((ResponseEntity<Object>) joinPoint.proceed(this.bindingArgument(args, encrypt)));
        }

        secret = RSAUtils.decrypt(secret, privateKey);
        var secretKey = secret.substring(8, secret.length() - 8);
        var iv = secret.substring(0, 8).concat(secret.substring(secret.length() - 8));
        var body = this.bindingArgument(args, AESUtils.decrypt(encrypt, secretKey, iv));
        var response = (ResponseEntity<Object>) joinPoint.proceed(body);

        return this.encryptResponse(response, secretKey, iv);
    }

    private void validateAccessToken(String token) {
        try {
//            keycloakFeignClient.getKeycloakUserInfo(keycloakRealm, token);
        } catch (Exception ex) {
            log.error(String.format("Get keycloak user info fail: %s", ex));
            throw new KAGatewayException(HttpStatus.UNAUTHORIZED, ResponseStatus.UNAUTHORIZED);
        }
    }

    private void validateRequest(String secret, String encrypt) {
        var isJsonValid = isJsonValid(encrypt);

        if (forceEncrypt && isJsonValid) {
            throw new KAGatewayException(HttpStatus.BAD_REQUEST, ResponseStatus.INVALID_REQUEST_BODY);
        }

        if (forceEncrypt && StringUtils.isBlank(secret)) {
            throw new KAGatewayException(HttpStatus.BAD_REQUEST, ResponseStatus.SECRET_KEY_NOT_FOUND);
        }
    }

    private Object[] bindingArgument(Object[] args, String body) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        var argsRequest = new Object[args.length];

        for (var i = 0; i < args.length; i++) {
            argsRequest[i] = mapper.readValue(body, args[i].getClass());
        }

        return argsRequest;
    }

    private ResponseEntity<Object> response(ResponseEntity<Object> response) {
//        var headers = breadCrumbService.getTracingHeader();
        var headers = new HttpHeaders();
        headers.set("file-name", response.getHeaders().getFirst("file-name"));

        return ResponseEntity.status(response.getStatusCode()).headers(headers).body(response.getBody());
    }

    private ResponseEntity<Object> encryptResponse(ResponseEntity<Object> response, String secretKey, String iv) throws BadPaddingException,
            NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException,
            UnsupportedEncodingException {

        var gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
//        var headers = breadCrumbService.getTracingHeader();
        var headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.TEXT_PLAIN_VALUE);
        headers.set("file-name", response.getHeaders().getFirst("file-name"));
        var resHeaders = response.getHeaders();
        var contentType = resHeaders.getContentType();

        if (contentType != null) {
            String encodeToString = Base64.getEncoder().encodeToString((byte[]) response.getBody());
            return ResponseEntity.status(response.getStatusCode())
                    .headers(headers)
                    .body(AESUtils.encrypt(encodeToString, secretKey, iv));
        }

        return ResponseEntity.status(response.getStatusCode())
                .headers(headers)
                .body(AESUtils.encrypt(gson.toJson(response.getBody()), secretKey, iv));
    }

    public static JsonNode parseJWT(String token) throws JsonProcessingException {
        token = token.replace("Bearer ", "");

        var splitString = token.split("\\.");
        var encodeBody = splitString[1];
        var base64 = new org.apache.commons.codec.binary.Base64(true);
        var body = new String(base64.decode(encodeBody));

        return new ObjectMapper().readValue(body, JsonNode.class);
    }

    public static boolean isJsonValid(String body) {
        try {
            new ObjectMapper().readTree(body);
        } catch (JsonProcessingException e) {
            return false;
        }

        return true;
    }
}
