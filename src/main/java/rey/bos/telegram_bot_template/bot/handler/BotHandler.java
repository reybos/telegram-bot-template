package rey.bos.telegram_bot_template.bot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.bot.handler.impl.callback.CallBackCommand;
import rey.bos.telegram_bot_template.bot.handler.impl.command.MenuCommand;
import rey.bos.telegram_bot_template.shared.dto.UserDto;

import static rey.bos.telegram_bot_template.bot.handler.MessageEntityType.BOT_COMMAND;

@Slf4j
public abstract class BotHandler {

    public abstract boolean handle(Update update, UserDto user);

    public abstract boolean support(Update update);

    public boolean supportMenuCommand(Update update, MenuCommand command) {
        return update.hasMessage() && update.getMessage().hasText()
            && (
                !CollectionUtils.isEmpty(update.getMessage().getEntities())
                && update.getMessage().getEntities()
                    .stream()
                    .filter(entity -> entity.getType().equals(BOT_COMMAND.getDescription()))
                    .map(MessageEntity::getText)
                    .anyMatch(command.getCommand()::equals)
        );
    }

    public boolean supportCallbackCommand(Update update, CallBackCommand command) {
        return update.hasCallbackQuery()
            && update.getCallbackQuery().getData().startsWith(command.getCommand());
    }

    public void logCall(long userId, String command, String data) {
        String logData = "userId = %d call command = \"%s\" with data = \"%s\"";
        log.info(String.format(logData, userId, command, data));
    }

}
