package di.fa.kakeep.grpc.channel;

import di.fa.kaproto.auth.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AuthGrpcStubConfiguration {
//    @Bean
//    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub(@Qualifier("AuthGrpcChannel") ManagedChannel channel) {
//        return UserServiceGrpc.newBlockingStub(channel);
//    }
}
