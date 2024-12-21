package com.backend.studycenter.configs.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class WebinarProducer {

    @Value("${rabbitmq.exchange_name}")
    private String exchangeName;

    @Value("${rabbitmq.webinar.routing_key}")
    private String webinarRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public WebinarProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public <T> void sendMessage(T dto) {
        rabbitTemplate.convertAndSend(exchangeName, webinarRoutingKey, dto);
    }

}
