package di.fa.kagateway.feign.controller;

import di.fa.kacommon.security.CredentialsHolder;
import di.fa.kagateway.feign.client.AuthFeignClient;
import di.fa.kagateway.feign.dto.request.RegisterModuleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/module")
public class ModuleFeignController {

    final AuthFeignClient authFeignClient;
    final CredentialsHolder credentialsHolder;

    @PostMapping("/register")
    public ResponseEntity<Object> registerModule(@RequestBody RegisterModuleRequest request) {
        return ResponseEntity.ok(authFeignClient.registerModule(request));
    }

    @GetMapping("/settings/{subDomain}")
    public ResponseEntity<Object> getModuleSettings(@PathVariable("subDomain") String subDomain) {
        return ResponseEntity.ok(authFeignClient.getModuleSettings(subDomain));
    }
}
