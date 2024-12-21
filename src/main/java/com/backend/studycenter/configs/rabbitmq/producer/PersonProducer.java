package com.backend.studycenter.configs.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PersonProducer {

    @Value("${rabbitmq.exchange_name}")
    private String exchangeName;

    @Value("${rabbitmq.person.routing_key}")
    private String personRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PersonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public <T> void sendMessage(T dto) {
        rabbitTemplate.convertAndSend(exchangeName, personRoutingKey, dto);
    }
}
