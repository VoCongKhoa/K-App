package di.fa.kagateway.grpc.client.impl;

import di.fa.kagateway.grpc.client.AccountGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountGrpcClientImpl implements AccountGrpcClient {

//    final AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub;
//
//    @Override
//    public AccountCommonResponse getAllAccounts() {
//        var credentialsHolder = CredentialsHolderProvider.getPrincipal();
//        return accountServiceBlockingStub
//                .withCallCredentials(CredentialsHolderProvider.asGrpcCredentials(credentialsHolder))
//                .listAllAccount(Empty.getDefaultInstance());
//    }
}
