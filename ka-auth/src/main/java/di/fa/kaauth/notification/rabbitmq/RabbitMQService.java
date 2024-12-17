package di.fa.kaauth.notification.rabbitmq;

import di.fa.kaauth.core.configs.RabbitMQConfiguration;
import di.fa.kaauth.core.rabbitmq.queue.message.SendMailRegisterModuleMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQService {

    final RabbitTemplate rabbitTemplate;
    final RabbitMQConfiguration mqConfiguration;

    public void publishMailRegisterModule() {
        try {
            var message = SendMailRegisterModuleMessage
                    .builder()
                    .email("principalService.getEmail()")
                    .build();

            rabbitTemplate.convertAndSend(mqConfiguration.mailRegisterModuleQueue, "", message);

            log.info(String.format("Publish email register module: %s successful."));
        } catch (Exception ex) {
            log.error(String.format("Publish email register module: %s fail with cause: %s message: %s"));
        }
    }
}
