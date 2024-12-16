package di.fa.kaauth.feign.service.impl;

import di.fa.kaauth.core.EmailService;
import di.fa.kaauth.feign.dto.request.RegisterModuleFeignRequest;
import di.fa.kaauth.feign.dto.response.GetModuleSettingsResponse;
import di.fa.kaauth.feign.dto.response.RegisterModuleFeignResponse;
import di.fa.kaauth.feign.service.ModuleFeignService;
import di.fa.kaauth.entity.ModuleEntity;
import di.fa.kaauth.entity.UserEntity;
import di.fa.kaauth.repository.ModuleRepository;
import di.fa.kacommon.common.Status;
import di.fa.kacommon.common.Type;
import di.fa.kacommon.response.SystemResponse;
import di.fa.kacommon.security.PassPhase;
import di.fa.kacommon.security.PwdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleFeignServiceImpl implements ModuleFeignService {

    final ModuleRepository moduleRepository;

    final EmailService emailService;

    @Override
    public SystemResponse<Object> registerModule(RegisterModuleFeignRequest request) {

        // todo: validate request

        var moduleId = UUID.randomUUID();
        var module = ModuleEntity
                .builder()
                .moduleId(moduleId)
                .moduleName(request.getModuleName())
                .moduleCode(request.getModuleSubName().toUpperCase(Locale.ROOT))
                .description(request.getDescription())
                .countryCode(request.getCountryCode())
                .phoneCode(request.getPhoneCode())
                .timezoneCode(request.getTimezoneCode())
                .email(request.getEmail())
                .status(Status.Module.NEWBIE.getStatus())
                .isPublic(false)
                .build();

        moduleRepository.save(module);

        var userId = UUID.randomUUID();
        var defaultPassword = PassPhase.getNext(); // todo: autogen default pwd
        var rootUser = UserEntity
                .builder()
                .userId(userId)
                .username(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(defaultPassword)
                .passwordHash(PwdUtil.encodeString(defaultPassword))
                .phoneCode(request.getPhoneCode())
                .phoneNumber(request.getPhoneNumber())
                .status(Status.User.ACTIVE.getStatus())
                .moduleId(moduleId)
                .build();

        // todo: send email
        emailService.sendRegistrationEmail(rootUser, Type.Email.REGISTER_001.getType());

        var response = SystemResponse.builder().build();
        response.asSuccess(SystemResponse.SUCCESS, RegisterModuleFeignResponse.builder().moduleId(moduleId.toString()).build());
        return response;
    }

    @Override
    public SystemResponse<Object> getModuleSettings(String subDomain) {
        var module = moduleRepository.findByModuleCode(subDomain).orElseThrow();
        var response = SystemResponse.builder().build();
        response.asSuccess(SystemResponse.SUCCESS, GetModuleSettingsResponse.of(module));
        return response;
    }
}
