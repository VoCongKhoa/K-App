package di.fa.kagateway.feign.controller;

import di.fa.kagateway.feign.client.AuthFeignClient;
import di.fa.kagateway.feign.dto.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthFeignController {

    final AuthFeignClient authFeignClient;

    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authFeignClient.login(request));
    }
}