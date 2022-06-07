package app.coreproject.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MyTelegramBot extends TelegramLongPollingBot {
    //    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyTelegramBot.class);
    @Value("${app.auth.telegramBotUsername}")
    private String telegramBotUsername;

    @Value("${app.auth.telegramBotToken}")
    private String telegramBotToken;

    private final WeatherApiService weatherApiService;
    @Override
    public void onUpdateReceived(Update update) {
        handleMessage(update.getMessage());
    }

    @SneakyThrows
    private void handleMessage(Message message) {
        if (message.hasText() && message.getText().startsWith("/")) {
            String command = message.getText();
            if (command.equals("/start")) {
                SendMessage response = new SendMessage();
                response.setChatId(message.getChatId().toString());
                response.setText("Hello");
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (command.equals("/weather")) {
                SendMessage response = new SendMessage();
                response.setChatId(message.getChatId().toString());
                //response.setText( weatherApiService.getInfomation("hanoi"));

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (command.contains("/weather:")) {
                SendMessage response = new SendMessage();
                SendPhoto sendPhoto = new SendPhoto();
                response.setChatId(message.getChatId().toString());
                sendPhoto.setChatId(message.getChatId().toString());
                String[] prm = command.split(":");
                Map<String, String> map = weatherApiService.getInfomation(prm[1]);
                if(map != null){
                    //response.setText("Thời tiết ngày: " + java.time.LocalDate.now()+ " có "+  map.get("description") + ".Nhiệt độ: " + map.get("temp") + "℃");
                    InputFile inputFile = new InputFile();
                    inputFile.setMedia(map.get("photo"));
                    sendPhoto.setPhoto(inputFile);
                    sendPhoto.setCaption("Thời tiết ngày: " + java.time.LocalDate.now()+ " có "+  map.get("description") + ".Nhiệt độ: " + map.get("temp") + "℃");
                }else {
                    response.setText("Incorrect!");
                }
                try {
                    //execute(response);
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotUsername;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

}

