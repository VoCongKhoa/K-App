package di.fa.kaauth.feign.client;

import di.fa.kaauth.configs.feign.FeignConfiguration;
import di.fa.kaauth.feign.dto.response.KeycloakLoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "keycloak-service", url = "${keycloak.endpoint}", configuration = FeignConfiguration.class)
public interface KeycloakServiceClient {

    @PostMapping(value = "/realms/{realms}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<KeycloakLoginResponse> login(@PathVariable("realms") String realms, Map<String, ?> request);

    @DeleteMapping("/admin/realms/{realms}/users/{userId}")
    ResponseEntity<Long> deleteUser(@RequestHeader("Authorization") String token, @PathVariable("realms") String realms, @PathVariable("userId") String userId);
}
