package di.fa.kaauth.grpc.service.impl;

import di.fa.kaauth.core.exception.KAAuthException;
import di.fa.kaauth.core.security.CredentialsHolder;
import di.fa.kaauth.grpc.service.UserGrpcService;
import di.fa.kaauth.core.repository.UserRepository;
import di.fa.kacommon.common.Status;
import di.fa.kaproto.auth.GetUserByUsernameRequest;
import di.fa.kaproto.auth.GetUserByUsernameResponse;
import di.fa.kaproto.common.CommonResponse;
import di.fa.kaproto.common.OnlyIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGrpcServiceImpl implements UserGrpcService {

    final UserRepository userRepository;

    @Override
    public GetUserByUsernameResponse getUserByUsername(GetUserByUsernameRequest request, CredentialsHolder credentialsHolder) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new KAAuthException(null));
        return GetUserByUsernameResponse.newBuilder()
                .setId(user.getUserId().toString())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .build();
    }

    @Override
    public CommonResponse lockUserByUserId(OnlyIdRequest request, CredentialsHolder credentialsHolder) {
        var user = userRepository.findById(UUID.fromString(request.getId())).orElseThrow(() -> new KAAuthException(null));
        user.setStatus(Status.User.BLOCK.getStatus());
        return CommonResponse.newBuilder()
                .setCode("")
                .setMessage("")
                .build();
    }
}
