package app.coreproject.config;

import app.coreproject.service.MyTelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TelegramConfig {
    private final MyTelegramBot mybot;

    @PostConstruct
    public void register() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(mybot);
    }
}