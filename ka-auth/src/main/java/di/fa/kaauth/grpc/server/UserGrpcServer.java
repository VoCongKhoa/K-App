package di.fa.kaauth.grpc.server;

import di.fa.kaauth.core.security.CredentialsHolder;
import di.fa.kaauth.grpc.channel.AuthServerInterceptor;
import di.fa.kaproto.auth.GetUserByUsernameRequest;
import di.fa.kaproto.auth.GetUserByUsernameResponse;
import di.fa.kaproto.auth.UserServiceGrpc;
import di.fa.kaproto.common.CommonResponse;
import di.fa.kaproto.common.OnlyIdRequest;
import io.grpc.stub.StreamObserver;
import di.fa.kaauth.grpc.service.UserGrpcService;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@RequiredArgsConstructor
@GRpcService(interceptors = {AuthServerInterceptor.class})
public class UserGrpcServer extends UserServiceGrpc.UserServiceImplBase {

    final UserGrpcService userGrpcService;

    @Override
    public void getUserByUsername(GetUserByUsernameRequest request, StreamObserver<GetUserByUsernameResponse> responseObserver) {
        try {
            CredentialsHolder credentialsHolder = null;
            responseObserver.onNext(userGrpcService.getUserByUsername(request, credentialsHolder));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onNext(GetUserByUsernameResponse.newBuilder()
//                    .setSuccess(false)
//                    .setError(AccountGrpcExceptionUtil.asGrpcError(e))
                    .build());
            responseObserver.onCompleted();
            throw e;
        }
    }

    public void lockUserByUserId(OnlyIdRequest request, StreamObserver<CommonResponse> responseObserver) {
        try {
            CredentialsHolder credentialsHolder = null;
            responseObserver.onNext(userGrpcService.lockUserByUserId(request, credentialsHolder));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onNext(CommonResponse.newBuilder()
//                    .setSuccess(false)
//                    .setError(AccountGrpcExceptionUtil.asGrpcError(e))
                    .build());
            responseObserver.onCompleted();
            throw e;
        }
    }

}
