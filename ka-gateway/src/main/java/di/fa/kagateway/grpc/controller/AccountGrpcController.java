package di.fa.kagateway.grpc.controller;

import di.fa.kacommon.security.CredentialsHolder;
import di.fa.kagateway.feign.client.AccountFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account")
public class AccountGrpcController {

    final AccountFeignClient accountFeignClient;
    final CredentialsHolder credentialsHolder;
}
