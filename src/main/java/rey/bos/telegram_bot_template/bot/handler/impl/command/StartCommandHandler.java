package rey.bos.telegram_bot_template.bot.handler.impl.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.shared.dto.UserDto;
import rey.bos.telegram_bot_template.util.BotUtil;
import rey.bos.telegram_bot_template.util.MessageUtil;

import static rey.bos.telegram_bot_template.bot.handler.impl.command.MenuCommand.MENU_COMMAND_START;
import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.GREETING_FOR_START;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartCommandHandler extends BotHandler {

    private final BotUtil botUtil;
    private final MessageUtil messageUtil;

    @Override
    public boolean handle(Update update, UserDto user) {
        logCall(user.getTelegramId(), MENU_COMMAND_START.getCommand(), "");
        String login = messageUtil.getLogin(user.getUserName());
        String text = botUtil.getText(user.getLanguageCode(), GREETING_FOR_START)
            .formatted(login);
        botUtil.sendMessage(user.getTelegramId(), text);
        return true;
    }

    @Override
    public boolean support(Update update) {
        return supportMenuCommand(update, MENU_COMMAND_START);
    }

}
