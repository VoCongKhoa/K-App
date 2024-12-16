package di.fa.kaauth.grpc.client;


import di.fa.kaproto.auth.GetUserByUsernameRequest;
import di.fa.kaproto.auth.GetUserByUsernameResponse;

public interface AccountGrpcClient {

    GetUserByUsernameResponse getUserByUsername(GetUserByUsernameRequest request);
}
