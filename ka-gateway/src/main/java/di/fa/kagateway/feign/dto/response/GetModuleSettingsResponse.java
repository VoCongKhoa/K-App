package di.fa.kagateway.feign.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetModuleSettingsResponse {
    private String accessToken;
    private String refreshToken;
    private String expiredAt;
    private String refreshExpiredAt;
}
