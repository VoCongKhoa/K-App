package di.fa.kaauth.grpc.client.impl;

import di.fa.kaauth.grpc.client.AccountGrpcClient;
import di.fa.kaauth.core.utils.SecurityUtils;
import di.fa.kaauth.core.security.CredentialsHolderProvider;
import di.fa.kaproto.auth.GetUserByUsernameRequest;
import di.fa.kaproto.auth.GetUserByUsernameResponse;
import di.fa.kaproto.auth.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountGrpcClientImpl implements AccountGrpcClient {

    final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @Override
    public GetUserByUsernameResponse getUserByUsername(GetUserByUsernameRequest request) {
        var credentialsHolder = SecurityUtils.getPrincipal();
        return userServiceBlockingStub
                .withCallCredentials(CredentialsHolderProvider.asGrpcCredentials(credentialsHolder))
                .getUserByUsername(request);
    }
}
