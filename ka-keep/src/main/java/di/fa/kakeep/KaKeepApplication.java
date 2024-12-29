package di.fa.kakeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"di.fa.kakeep.feign.client"})
public class KaKeepApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaKeepApplication.class, args);
    }

}
