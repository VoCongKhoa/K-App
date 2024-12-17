package di.fa.kaauth.core.configs;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${mq.register.module.queue}")
    public String mailRegisterModuleQueue;
    @Value("${mq.register.module.queue.dlq}")
    public String mailRegisterModuleQueueDlq;

    @Value("${mq.register.user.queue}")
    public String mailRegisterUserQueue;
    @Value("${mq.register.user.queue.dlq}")
    public String mailRegisterUserQueueDlq;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
