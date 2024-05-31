package rey.bos.telegram_bot_template.bot.handler.impl.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.shared.dto.UserDto;
import rey.bos.telegram_bot_template.util.BotUtil;
import rey.bos.telegram_bot_template.util.MessageUtil;

import static rey.bos.telegram_bot_template.bot.handler.MessageEntityType.BOT_COMMAND;
import static rey.bos.telegram_bot_template.bot.handler.MessageEntityType.MENTION;
import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.ECHO_MESSAGE;

@Component
@RequiredArgsConstructor
@Slf4j
public class EchoHandler extends BotHandler {

    private final BotUtil botUtil;
    private final MessageUtil messageUtil;

    @Override
    public boolean handle(Update update, UserDto user) {
        String text = update.getMessage().getText();
        SendMessage sendMessage = messageUtil.buildSendMessage(user, ECHO_MESSAGE, text);
        botUtil.executeMethod(sendMessage);
        return true;
    }

    @Override
    public boolean support(Update update) {
        return update.hasMessage() && update.getMessage().hasText()
            && (CollectionUtils.isEmpty(update.getMessage().getEntities())
                || update.getMessage().getEntities()
                    .stream()
                    .map(MessageEntity::getType)
                    .noneMatch(
                        type -> type.equals(BOT_COMMAND.getDescription()) || type.equals(MENTION.getDescription())
                    ));
    }

}
