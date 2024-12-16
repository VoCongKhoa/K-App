package di.fa.kagateway.feign.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
    public String id;
    public String username;
    public String email;
}
