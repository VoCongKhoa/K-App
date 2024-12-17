package di.fa.kanotification.rabbitmq.subscribe;

import di.fa.kanotification.rabbitmq.queue.message.SendMailRegisterModuleMessage;
import di.fa.kanotification.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailMessageSubscribe {

    final MailService mailService;

    private static final String E_L101 = "E-L101";


    @RabbitListener(queues = "${mq.email.otp-locked.queue}")
    public void subscribeEmailOTPLockedMessage(SendMailRegisterModuleMessage message) {
        try {
            mailService.sendMailRegisterModule(message, E_L101);
        } catch (Exception ex) {
            log.error(String.format("Subscribe email otp locked error cause: %s message: %s", ex, ex.getMessage()), ex);
            throw new AmqpRejectAndDontRequeueException(String.format("Subscribe email otp locked error cause: %s message: %s", ex, ex.getMessage()), ex);
        }
    }
}
