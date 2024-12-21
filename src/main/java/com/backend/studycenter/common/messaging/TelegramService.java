package com.backend.studycenter.common.messaging;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramService extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "UnlimitAcademyBot";
    }

    @Override
    public String getBotToken() {
        return "6002141663:AAFF2Ekg95gTeRYX2tBsAq6NasPSagwJcQw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var msg = update.getMessage();
            var user = msg.getFrom();
            var Id = user.getId();
            try {
                var welcomeMessage = "Hello " + user.getFirstName() + ". Welcome to our bot!\nYour register code for unlimit.uz: " + Id;
                sendText(Id, welcomeMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendText(Long userId, String text) throws TelegramApiException {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(userId)
                .text(text).build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
