package di.fa.kagateway.feign.client;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kagateway.core.configs.feign.FeignConfiguration;
import di.fa.kagateway.feign.dto.request.CreateBookRequest;
import di.fa.kagateway.feign.dto.request.GreetingRequest;
import di.fa.kagateway.feign.dto.request.SearchBookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "learning-service", url = "${ka.learning.service.endpoint}", configuration = FeignConfiguration.class)
public interface LearningFeignClient {

    @PostMapping("/v1/greeting")
    SystemResponse<Object> greeting(@RequestBody GreetingRequest request);

    @PostMapping("/v1/book/create")
    SystemResponse<Object> createBook(CreateBookRequest request);

    @PostMapping("/v1/book/search")
    SystemResponse<Object> searchBook(SearchBookRequest request);
}
