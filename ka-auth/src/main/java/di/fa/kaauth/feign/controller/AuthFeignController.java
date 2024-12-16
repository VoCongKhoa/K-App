package di.fa.kaauth.feign.controller;

import di.fa.kaauth.feign.dto.request.LoginRequest;
import di.fa.kaauth.feign.service.AuthFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthFeignController {

    final AuthFeignService authFeignService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authFeignService.login(request));
    }
}
