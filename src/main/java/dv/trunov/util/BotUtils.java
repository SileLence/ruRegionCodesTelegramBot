package dv.trunov.util;

import dv.trunov.model.Region;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class BotUtils {

    public static String getRegionAnswer(Region region) {
        return String.format(
            "%s\n\nКод: %d\n\nСтолица: %s\n\n%s\n\n[Подробнее](%s)",
            region.getName(),
            region.getCode(),
            region.getCapital(),
            region.getDescription(),
            region.getUrl()
        );
    }

    public static String getGreeting(Update update) {
        return String.format(
            "Привет, %s!\nЯ помогу тебе узнать название региона России по его коду. Отправь мне число, чтобы узнать ответ.",
            update.getMessage().getFrom().getFirstName());
    }

    public static SendMessage getSendMessage(String text, Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.enableMarkdown(true);
        sendMessage.disableWebPagePreview();
        sendMessage.setChatId(update.getMessage().getChatId());
        return sendMessage;
    }
}
