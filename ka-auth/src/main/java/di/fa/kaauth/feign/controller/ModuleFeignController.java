package di.fa.kaauth.feign.controller;

import di.fa.kaauth.feign.dto.request.RegisterModuleFeignRequest;
import di.fa.kaauth.feign.service.ModuleFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
public class ModuleFeignController {

    final ModuleFeignService moduleFeignService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerModule(@RequestBody RegisterModuleFeignRequest request) {
        return ResponseEntity.ok(moduleFeignService.registerModule(request));
    }

    @GetMapping("/settings/{subDomain}")
    public ResponseEntity<Object> getModuleSettings(@PathVariable("subDomain") String subDomain) {
        return ResponseEntity.ok(moduleFeignService.getModuleSettings(subDomain));
    }
}
