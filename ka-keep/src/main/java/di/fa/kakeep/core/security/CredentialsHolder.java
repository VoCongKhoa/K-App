package di.fa.kakeep.core.security;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@NoArgsConstructor
@AllArgsConstructor
@Component
@RequestScope
@Getter
@Setter
public class CredentialsHolder {
    private JsonNode userInfo;
    private String moduleId;
    private String refreshToken;
    private String accessToken;
    private String secretKey;
}
