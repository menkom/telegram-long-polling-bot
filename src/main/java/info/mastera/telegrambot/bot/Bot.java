package info.mastera.telegrambot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${telegram.token}")
    private String botToken;
    @Value("${telegram.username}")
    private String botUsername;


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Update:" + update);
        try {
            Message message = update.getMessage();
            execute(new SendMessage(message.getChatId().toString(), "Bot received this message:" + message.getText()));
        } catch (TelegramApiException e) {
            log.error("Error sending message to user.", e);
        }
    }
}
