package di.fa.kaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackages = {"di.fa.kaauth.feign.client"})
@ImportAutoConfiguration(value = FeignAutoConfiguration.class)
public class KaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaAuthApplication.class, args);
    }

}
