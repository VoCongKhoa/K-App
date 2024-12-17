package di.fa.kagateway.feign.client;

import di.fa.kacommon.response.SystemResponse;
import di.fa.kagateway.core.configs.feign.FeignConfiguration;
import di.fa.kagateway.feign.dto.request.LoginRequest;
import di.fa.kagateway.feign.dto.request.RegisterModuleRequest;
import di.fa.kagateway.feign.dto.request.RegisterUserRequest;
import di.fa.kagateway.feign.dto.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service", url = "${ka.auth.service.endpoint}", configuration = FeignConfiguration.class)
public interface AuthFeignClient {
    String AUTHORIZATION = "Authorization";

    @PostMapping("/auth/login")
    SystemResponse<LoginResponse> login(
            @RequestBody LoginRequest request
    );

    @GetMapping("/user/register")
    SystemResponse<RegisterUserResponse> registerUser(
            @RequestHeader(AUTHORIZATION) String accessToken,
            @RequestBody RegisterUserRequest request
    );

    @GetMapping("/user/all")
    SystemResponse<GetAllUsersResponse> getAllUsers(
            @RequestHeader(AUTHORIZATION) String accessToken
    );

    @GetMapping("/user/settings/{userId}")
    SystemResponse<GetUserSettingsResponse> getUserSettings(
            @RequestHeader(AUTHORIZATION) String accessToken,
            @PathVariable("userId") String userId
    );

    @PostMapping("/module/register")
    SystemResponse<RegisterModuleResponse> registerModule(
            @RequestBody RegisterModuleRequest request
    );

    @GetMapping("/module/settings/{subDomain}")
    SystemResponse<GetModuleSettingsResponse> getModuleSettings(
            @PathVariable("subDomain") String subDomain
    );
}
