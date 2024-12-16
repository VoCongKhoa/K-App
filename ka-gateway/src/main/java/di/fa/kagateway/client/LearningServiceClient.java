package di.fa.kagateway.client;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kagateway.core.configuration.FeignClientConfiguration;
import di.fa.kagateway.feign.dto.request.CreateBookRequest;
import di.fa.kagateway.feign.dto.request.GreetingRequest;
import di.fa.kagateway.feign.dto.request.SearchBookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "learning-service", url = "${service.learning.url}", configuration = FeignClientConfiguration.class)
public interface LearningServiceClient {

    @PostMapping("/v1/greeting")
    SystemResponse<Object> greeting(@RequestBody GreetingRequest request);

    @PostMapping("/v1/book/create")
    SystemResponse<Object> createBook(CreateBookRequest request);

    @PostMapping("/v1/book/search")
    SystemResponse<Object> searchBook(SearchBookRequest request);
}
