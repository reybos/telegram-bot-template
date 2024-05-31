package rey.bos.telegram_bot_template.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import rey.bos.telegram_bot_template.dictionary.Dictionary;
import rey.bos.telegram_bot_template.dictionary.DictionaryKey;
import rey.bos.telegram_bot_template.shared.dto.LanguageCode;

import java.io.Serializable;
import java.util.List;

import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.SOMETHING_WENT_WRONG;

@Component
@AllArgsConstructor
@Slf4j
public class BotUtil {

    private final TelegramClient telegramClient;
    private final List<Dictionary> dictionaries;

    public void sendMessageByKey(Long chatId, LanguageCode languageCode, DictionaryKey key) {
        String message = getText(languageCode, key);
        sendMessage(chatId, message);
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = SendMessage // Create a message object
            .builder()
            .parseMode("HTML")
            .chatId(chatId)
            .text(text)
            .build();
        executeMethod(message);
    }

    public void sendSomethingWentMessage(Long chatId, LanguageCode languageCode) {
        sendMessageByKey(chatId, languageCode, SOMETHING_WENT_WRONG);
    }

    public <T extends Serializable> void executeMethod(BotApiMethod<T> message) {
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("Can't execute command", e);
        }
    }

    public String getText(LanguageCode languageCode, DictionaryKey key) {
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.isSuitable(languageCode)) {
                return dictionary.get(key);
            }
        }
        return "";
    }

}
