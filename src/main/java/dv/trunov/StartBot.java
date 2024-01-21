package dv.trunov;

import dv.trunov.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartBot {

    public static void main(String[] args) throws TelegramApiException {
        new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}
