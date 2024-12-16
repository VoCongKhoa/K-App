package di.fa.kaauth.grpc.channel;

import di.fa.kaproto.auth.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AccountGrpcChannelConfiguration {

    private final GrpcSlowQueryDetectingInterceptor loggingClientInterceptor;

    @Bean(value = "LearningGrpcChannel")
    public ManagedChannel learningGrpcChannel() {
        var host = "localhost";
        var port = 3333;
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

//    @Bean
//    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub(@Qualifier("AccountGrpcChannel") ManagedChannel channel) {
//        return UserServiceGrpc.newBlockingStub(channel)
//                .withInterceptors(loggingClientInterceptor);
//    }
}
