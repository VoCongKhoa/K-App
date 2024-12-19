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
                    .firstName("test")
                    .lastName("test")
                    .password("test")
                    .email("voboiboi.0403@gmail.com")
                    .build();

            rabbitTemplate.convertAndSend(mqConfiguration.mailRegisterModuleQueue, "", message);

            log.info("Publish email register module successful.");
        } catch (Exception ex) {
            log.error(String.format("Publish email register module fail with cause: %s message: %s", ex.getCause().toString(), ex.getMessage()));
        }
    }
}
