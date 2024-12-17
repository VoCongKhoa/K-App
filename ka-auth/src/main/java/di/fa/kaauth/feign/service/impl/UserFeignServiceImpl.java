package di.fa.kaauth.feign.service.impl;

import di.fa.kaauth.core.security.CredentialsHolder;
import di.fa.kaauth.feign.dto.request.RegisterUserRequest;
import di.fa.kaauth.feign.service.KeycloakService;
import di.fa.kaauth.feign.service.UserFeignService;
import di.fa.kaauth.core.repository.UserRepository;
import di.fa.kacommon.response.SystemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFeignServiceImpl implements UserFeignService {

    final UserRepository userRepository;
    final KeycloakService keycloakService;
    final CredentialsHolder credentialsHolder;


    @Override
    public SystemResponse<Object> getAllUsers() {
        return null;
    }

    @Override
    public SystemResponse<Object> getUserSettings(String userId) {
        return null;
    }

    @Override
    public SystemResponse<Object> registerUser(RegisterUserRequest request) {
        return null;
    }
}
