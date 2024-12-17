package di.fa.kaauth.grpc.service;


import di.fa.kaauth.core.security.CredentialsHolder;
import di.fa.kaproto.auth.GetUserByUsernameRequest;
import di.fa.kaproto.auth.GetUserByUsernameResponse;
import di.fa.kaproto.common.CommonResponse;
import di.fa.kaproto.common.OnlyIdRequest;

public interface UserGrpcService {
    GetUserByUsernameResponse getUserByUsername(GetUserByUsernameRequest request, CredentialsHolder credentialsHolder);

    CommonResponse lockUserByUserId(OnlyIdRequest request, CredentialsHolder credentialsHolder);
}
