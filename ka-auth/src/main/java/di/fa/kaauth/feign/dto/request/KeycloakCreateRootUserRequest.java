package di.fa.kaauth.feign.dto.request;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakCreateRootUserRequest {
    private UUID userId;
    private String username;
    private String password;
    private String passwordHash;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private String avatar;
    private String firstName;
    private String lastName;
    private String status;
    private Timestamp activeDate;
    private UUID moduleId;
}
