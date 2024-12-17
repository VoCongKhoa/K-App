package di.fa.kaauth.core.rabbitmq.queue.binding;

import di.fa.kaauth.core.configs.RabbitMQConfiguration;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class NotificationBinding {

    private static final String EXCHANGE = "x-dead-letter-exchange";
    private static final String ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    public Declarables mailRegisterModuleQueueConfig(RabbitMQConfiguration mqConfiguration) {
        var dlq = QueueBuilder.durable(mqConfiguration.mailRegisterModuleQueueDlq).build();
        var queue = QueueBuilder.durable(mqConfiguration.mailRegisterModuleQueue)
                .withArgument(EXCHANGE, "")
                .withArgument(ROUTING_KEY, mqConfiguration.mailRegisterModuleQueueDlq)
                .build();

        var topic = new FanoutExchange(mqConfiguration.mailRegisterModuleQueue);
        return new Declarables(queue, dlq, topic, bind(queue).to(topic));
    }

    @Bean
    public Declarables mailRegisterUserQueueConfig(RabbitMQConfiguration mqConfiguration) {
        var dlq = QueueBuilder.durable(mqConfiguration.mailRegisterUserQueueDlq).build();
        var queue = QueueBuilder.durable(mqConfiguration.mailRegisterUserQueue)
                .withArgument(EXCHANGE, "")
                .withArgument(ROUTING_KEY, mqConfiguration.mailRegisterUserQueueDlq)
                .build();

        var topic = new FanoutExchange(mqConfiguration.mailRegisterUserQueue);
        return new Declarables(queue, dlq, topic, bind(queue).to(topic));
    }
}
