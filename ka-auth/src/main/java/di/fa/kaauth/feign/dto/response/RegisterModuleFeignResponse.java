package di.fa.kaauth.feign.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterModuleFeignResponse {
    private String moduleId;
}
