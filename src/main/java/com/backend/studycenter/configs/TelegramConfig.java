package com.backend.studycenter.configs;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.messaging.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramConfig {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Bean
    public TelegramBotsApi telegramBotsApi() throws Exception {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Scope(scopeName = "Singleton")
    @Bean
    public TelegramService telegramService(TelegramBotsApi telegramBotsApi) {
        TelegramService telegramService = new TelegramService();
//        try {
//            telegramBotsApi.registerBot(telegramService);
//        } catch (TelegramApiException e) {
//            logger.error("Issue is happened in Telegram " + util.getMethodName(), e);
//        }
        return telegramService;
    }
}
