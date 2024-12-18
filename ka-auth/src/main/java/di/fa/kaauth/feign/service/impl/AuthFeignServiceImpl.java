package di.fa.kaauth.feign.service.impl;

import di.fa.kaauth.core.entity.LoginTrackingEntity;
import di.fa.kaauth.core.security.CredentialsHolder;
import di.fa.kaauth.feign.dto.request.LoginRequest;
import di.fa.kaauth.feign.service.AuthFeignService;
import di.fa.kaauth.feign.service.KeycloakService;
import di.fa.kaauth.grpc.client.AccountGrpcClient;
import di.fa.kaauth.core.repository.LoginTrackingRepository;
import di.fa.kacommon.common.Status;
import di.fa.kacommon.response.SystemResponse;
import di.fa.kaproto.auth.GetUserByUsernameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthFeignServiceImpl implements AuthFeignService {

    final AccountGrpcClient accountGrpcClient;
    final KeycloakService keycloakService;

    final LoginTrackingRepository loginTrackingRepository;

    final CredentialsHolder credentialsHolder;

    @Override
    @Transactional
    public SystemResponse<Object> login(LoginRequest request) {
        var moduleId = UUID.fromString("1467eba1-c4d0-4157-b37a-6ea061689f27");

        try {
            var keycloakResponse = keycloakService.loginUsername(request.getUsername(), request.getPassword());
            System.out.println(keycloakResponse);

//            // Call internal to account-service to get user entity
//            var user = accountGrpcClient.getUserByUsername(GetUserByUsernameRequest.newBuilder()
//                    .setUsername(request.getUsername())
//                    .build());
//
//            // todo: record loginTracking
//            var loginTracking = loginTrackingRepository.findByUser_UsernameAndModule_ModuleId(request.getUsername(), moduleId)
//                    .orElseGet(() -> LoginTrackingEntity
//                            .builder()
////                            .userId(userId)
////                            .moduleId(moduleId)
//                            .build());
//            loginTracking.setStatus(Status.Login.SUCCESS.getStatus());
//            loginTracking.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
//            loginTracking.setFailedCount(0);
//            loginTrackingRepository.save(loginTracking);
        } catch (Exception e) {
//            var loginTrackingOpt = loginTrackingRepository.findByUser_UsernameAndModule_ModuleId(request.getUsername(), moduleId);
//            LoginTrackingEntity loginTracking;
//            if (loginTrackingOpt.isPresent()) {
//                loginTracking = loginTrackingOpt.get();
//                var failedCount = loginTracking.getFailedCount();
//                if (failedCount == 3) {
//                    loginTracking.setStatus(Status.Login.FAILED.getStatus());
//                    // todo: lock user here!
//                } else {
//                    loginTracking.setFailedCount(++failedCount);
//                }
//            } else {
//                loginTracking = LoginTrackingEntity
//                        .builder()
////                        .userId(userId)
////                        .moduleId(moduleId)
//                        .status(Status.Login.FAILED.getStatus())
//                        .failedCount(1)
//                        .lastLoginTime(new Timestamp(System.currentTimeMillis()))
//                        .build();
//            }
//            loginTrackingRepository.save(loginTracking);
        }

        var response = SystemResponse.builder().build();
        response.asSuccess(SystemResponse.SUCCESS, null);
        return response;
    }
}
