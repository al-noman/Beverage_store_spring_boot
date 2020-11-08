package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({RabbitMQConfigConstants.class})
public class RabbitMQConfiguration {

//    public static final String BEVERAGE_STORE_QUEUE = "beverage_store_queue";
//    public static final String BEVERAGE_STORE_EXCHANGE = "beverage_store_exchange";
//    public static final String BEVERAGE_STORE_ROUTING_KEY = "beverage_store_routing_key";

    private final RabbitMQConfigConstants configConstants;

    public RabbitMQConfiguration(RabbitMQConfigConstants configConstants) {
        this.configConstants = configConstants;
    }

    @Bean
    public Queue queue(){
        return new Queue(configConstants.getQueue());
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(configConstants.getExchange());
    }

    @Bean
    public Binding binding(TopicExchange exchange, Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(configConstants.getRoutingKey());
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
