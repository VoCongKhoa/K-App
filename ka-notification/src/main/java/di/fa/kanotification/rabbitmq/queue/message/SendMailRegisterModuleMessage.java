package di.fa.kanotification.rabbitmq.queue.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailRegisterModuleMessage {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
