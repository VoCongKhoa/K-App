package di.fa.kagateway.feign.controller;

import di.fa.kagateway.core.security.CredentialsHolder;
import di.fa.kagateway.feign.client.KeepFeignClient;
import di.fa.kagateway.feign.dto.request.UpsertDelMemoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/memo")
public class MemoFeignController {

    final KeepFeignClient keepFeignClient;
    final CredentialsHolder credentialsHolder;

    @PutMapping()
    public ResponseEntity<Object> upsertDelMemo(@RequestBody UpsertDelMemoRequest request) {
        return ResponseEntity.ok(keepFeignClient.upsertDelMemo(credentialsHolder.getAccessToken(), request));
    }
}
