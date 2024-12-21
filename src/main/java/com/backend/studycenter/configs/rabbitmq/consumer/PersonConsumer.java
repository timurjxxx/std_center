package com.backend.studycenter.configs.rabbitmq.consumer;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.person.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PersonConsumer {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Async
    @RabbitListener(queues = "${rabbitmq.person.queue_name}")
    public void handleMessage(PersonDTO person) {
        //TODO: send email to person;
        logger.info("email for person: " + person.getName());
    }
}
