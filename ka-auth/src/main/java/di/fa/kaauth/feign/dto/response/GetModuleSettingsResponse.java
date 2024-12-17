package di.fa.kaauth.feign.dto.response;

import di.fa.kaauth.core.entity.ModuleEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetModuleSettingsResponse {
    private String moduleId;
    private String moduleName;
    private String moduleCode;
    private String status;
    private Boolean isPublic;
    private String phoneCode;
    private String countryCode;
    private String timezoneCode;
    private String email;

    public static GetModuleSettingsResponse of(ModuleEntity module) {
        return GetModuleSettingsResponse
                .builder()
                .moduleId(module.getModuleId().toString())
                .moduleName(module.getModuleName())
                .moduleCode(module.getModuleCode())
                .status(module.getStatus())
                .isPublic(module.getIsPublic())
                .phoneCode(module.getPhoneCode())
                .countryCode(module.getCountryCode())
                .timezoneCode(module.getTimezoneCode())
                .email(module.getEmail())
                .build();
    }
}
