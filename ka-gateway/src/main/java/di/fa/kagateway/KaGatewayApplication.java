package di.fa.kagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"di.fa.kagateway.feign.client"})
public class KaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaGatewayApplication.class, args);
    }

}
