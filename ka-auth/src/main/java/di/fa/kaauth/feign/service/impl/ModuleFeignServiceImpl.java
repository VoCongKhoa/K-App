package di.fa.kaauth.feign.service.impl;

import di.fa.kaauth.core.entity.*;
import di.fa.kaauth.core.repository.*;
import di.fa.kaauth.feign.dto.request.KeycloakCreateRootUserRequest;
import di.fa.kaauth.feign.dto.request.RegisterModuleFeignRequest;
import di.fa.kaauth.feign.dto.response.GetModuleSettingsResponse;
import di.fa.kaauth.feign.dto.response.RegisterModuleFeignResponse;
import di.fa.kaauth.feign.service.KeycloakService;
import di.fa.kaauth.feign.service.ModuleFeignService;
import di.fa.kaauth.notification.rabbitmq.RabbitMQService;
import di.fa.kacommon.common.Status;
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
    final UserRepository userRepository;
    final PermissionRepository permissionRepository;
    final RoleRepository roleRepository;
    final UserScopeRepository userScopeRepository;

    final KeycloakService keycloakService;
    final RabbitMQService mqService;

    @Override
    public SystemResponse<Object> registerModule(RegisterModuleFeignRequest request) {

        // todo: validate request
        // moduleCode: only a-z,0-9,_

        // Add MODULE
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

        // Add ROOT ROLE
        var roleId = UUID.randomUUID();
        var rootRole = RoleEntity
                .builder()
                .roleId(roleId)
                .roleCode("ROOT_ROLE_" + module.getModuleCode())
                .roleName("Root Role " + module.getModuleCode())
                .description("Root Role for Module " + module.getModuleName())
                .status(Status.Role.ACTIVE.getStatus())
                .isDefault(true)
                .module(module)
                .build();
        roleRepository.save(rootRole);

        // Add ROOT ROLE
        var permissionId = UUID.randomUUID();
        var rootPermission = PermissionEntity
                .builder()
                .permissionId(permissionId)
                .permissionCode("ROOT_PERMISSION_" + module.getModuleCode())
                .permissionName("Root Permission " + module.getModuleCode())
                .description("Root Permission for Module " + module.getModuleName())
                .status(Status.Permission.ACTIVE.getStatus())
                .isSystem(true)
                .module(module)
                .build();
        permissionRepository.save(rootPermission);

        // Add ROOT USER
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
                .module(module)
                .build();
        userRepository.save(rootUser);
        var rootUserReq = KeycloakCreateRootUserRequest
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
                .moduleId(module.getModuleId())
                .build();

        // Assign ROOT ROLE to ROOT USER
        var userScopeId = UUID.randomUUID();
        var rootUserScope = UserScopeEntity
                .builder()
                .userScopeId(userScopeId)
                .permission(rootPermission)
                .role(rootRole)
                .user(rootUser)
                .build();
        userScopeRepository.save(rootUserScope);

        // todo: send username, pwd to email
        mqService.publishMailRegisterModule();
//        emailService.sendRegistrationEmail(rootUser, Type.Email.REGISTER_001.getType());

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
