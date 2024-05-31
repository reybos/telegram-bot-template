package rey.bos.telegram_bot_template.bot.handler.impl.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.shared.dto.UserDto;
import rey.bos.telegram_bot_template.util.BotUtil;
import rey.bos.telegram_bot_template.util.MessageUtil;

import java.util.List;

import static rey.bos.telegram_bot_template.bot.handler.impl.callback.CallBackCommand.MAKE_DECISION;
import static rey.bos.telegram_bot_template.bot.handler.impl.command.MenuCommand.MENU_COMMAND_MAKE_DECISION;
import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class MakeDecisionCommandHandler extends BotHandler {

    private final BotUtil botUtil;
    private final MessageUtil messageUtil;

    @Override
    public boolean handle(Update update, UserDto user) {
        logCall(user.getTelegramId(), MENU_COMMAND_MAKE_DECISION.getCommand(), "");

        List<InlineKeyboardRow> buttons = messageUtil.buildYesNoButtons(user, MAKE_DECISION);
        SendMessage message = messageUtil.buildSendMessageWithButtons(user, MAKE_DECISION_MESSAGE, buttons);
        botUtil.executeMethod(message);
        return true;
    }

    @Override
    public boolean support(Update update) {
        return supportMenuCommand(update, MENU_COMMAND_MAKE_DECISION);
    }

}
