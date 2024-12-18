package di.fa.kagateway.feign.client;

import di.fa.kagateway.core.configs.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "keycloak-service", url = "${keycloak.endpoint}", configuration = FeignConfiguration.class)
public interface KeycloakFeignClient {

    @GetMapping("/realms/{realms}/protocol/openid-connect/userinfo")
    Object getKeycloakUserInfo(@PathVariable("realms") String realms, @RequestHeader("Authorization") String accessToken);
}
