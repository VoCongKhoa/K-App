package di.fa.kagateway.feign.controller;

import di.fa.kacommon.security.CredentialsHolder;
import di.fa.kagateway.feign.client.AuthFeignClient;
import di.fa.kagateway.feign.dto.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserFeignController {

    final AuthFeignClient authFeignClient;
    final CredentialsHolder credentialsHolder;

    @GetMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(authFeignClient.registerUser(
                credentialsHolder.getAccessToken(),
                request
        ));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(authFeignClient.getAllUsers(
                credentialsHolder.getAccessToken()
        ));
    }

    @GetMapping("/settings/{userId}")
    public ResponseEntity<Object> getUserSettings(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(authFeignClient.getUserSettings(
                credentialsHolder.getAccessToken(),
                userId
        ));
    }
}
