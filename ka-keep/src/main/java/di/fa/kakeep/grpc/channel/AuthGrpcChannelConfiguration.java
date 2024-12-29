package di.fa.kakeep.grpc.channel;

import di.fa.kaproto.auth.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AuthGrpcChannelConfiguration {

    @Bean(value = "AuthGrpcChannel")
    public ManagedChannel learningGrpcChannel() {
        var host = "localhost";
        var port = 2020;
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

    @Bean
    @DependsOn("AuthGrpcChannel")
    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub(@Qualifier("AuthGrpcChannel") ManagedChannel channel) {
        return UserServiceGrpc.newBlockingStub(channel);
    }
}
