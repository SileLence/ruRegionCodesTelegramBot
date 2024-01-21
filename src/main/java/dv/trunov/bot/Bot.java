package dv.trunov.bot;

import dv.trunov.config.BotConfig;
import dv.trunov.dao.RegionDAO;
import dv.trunov.model.Region;
import dv.trunov.util.BotUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    private final BotConfig botConfig;
    private final RegionDAO regionDAO;

    public Bot(BotConfig botConfig, RegionDAO regionDAO) {
        super(botConfig.getBotToken());
        this.botConfig = botConfig;
        this.regionDAO = regionDAO;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        LOGGER.info("Get message from User: {}", update.getMessage().getFrom());
        if (update.hasMessage()) {
            try {
                String message = update.getMessage().getText();
                if ("/start".equals(message)) {
                    sendGreeting(update);
                } else if (StringUtils.isNumeric(message)) {
                    Region region = regionDAO.findRegionByCode(message);
                    sendRegionInfo(region, update);
                }
            } catch (TelegramApiException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    private void sendGreeting(Update update) throws TelegramApiException {
        String greeting = BotUtils.getGreeting(update);
        SendMessage sendMessage = BotUtils.getSendMessage(greeting, update);
        execute(sendMessage);
    }

    private void sendRegionInfo(Region region, Update update) throws TelegramApiException {
        String answer = BotUtils.getRegionAnswer(region);
        SendMessage sendMessage = BotUtils.getSendMessage(answer, update);
        execute(sendMessage);
    }
}
