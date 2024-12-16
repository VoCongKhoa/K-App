package di.fa.kaauth.feign.service;

import di.fa.kaauth.feign.dto.request.RegisterUserRequest;
import di.fa.kacommon.response.SystemResponse;

public interface UserFeignService {

    SystemResponse<Object> getAllUsers();

    SystemResponse<Object> getUserSettings(String userId);

    SystemResponse<Object> registerUser(RegisterUserRequest request);
}
