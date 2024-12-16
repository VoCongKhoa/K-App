package di.fa.kaauth.feign.service;

import di.fa.kaauth.feign.dto.request.LoginRequest;
import di.fa.kacommon.response.SystemResponse;

public interface AuthFeignService {

    SystemResponse<Object> login(LoginRequest request);
}
