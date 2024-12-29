package di.fa.kagateway.feign.client;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kagateway.core.configs.feign.FeignConfiguration;
import di.fa.kagateway.feign.dto.request.UpsertDelMemoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "keep-service", url = "${ka.auth.service.endpoint}", configuration = FeignConfiguration.class)
public interface KeepFeignClient {
    String AUTHORIZATION = "Authorization";

    @PostMapping("/memo")
    SystemResponse<Object> upsertDelMemo(
            @RequestHeader(AUTHORIZATION) String accessToken,
            @RequestBody UpsertDelMemoRequest request
    );
}
