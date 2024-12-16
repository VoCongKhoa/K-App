package di.fa.kaauth.feign.controller;

import di.fa.kaauth.feign.dto.request.RegisterUserRequest;
import di.fa.kaauth.feign.service.UserFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserFeignController {

    final UserFeignService userFeignService;

    @GetMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(userFeignService.registerUser(request));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userFeignService.getAllUsers());
    }

    @GetMapping("/settings/{userId}")
    public ResponseEntity<Object> getUserSettings(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userFeignService.getUserSettings(userId));
    }
}
