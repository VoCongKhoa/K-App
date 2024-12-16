package di.fa.kagateway.feign.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterModuleRequest {
    private String moduleName;
    private String moduleSubName;
    private String description;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private String countryCode;
    private String timezoneCode;
}
