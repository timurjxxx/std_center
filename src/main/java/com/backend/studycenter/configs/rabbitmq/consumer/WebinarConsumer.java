package com.backend.studycenter.configs.rabbitmq.consumer;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.sccontrol.dto.WebinarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class WebinarConsumer {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Async
    @RabbitListener(queues = "${rabbitmq.webinar.queue_name}")
    public void handleMessage(WebinarDTO webinar) {
        for (var participant : webinar.getParticipants()) {
            //TODO: send email to participants;
            logger.info("email for webinar: " + webinar.getTitle() + " to person with Id = " + participant.getId());
        }
    }
}
