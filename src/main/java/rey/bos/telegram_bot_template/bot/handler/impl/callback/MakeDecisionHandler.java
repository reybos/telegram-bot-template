package rey.bos.telegram_bot_template.bot.handler.impl.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.shared.dto.UserDto;
import rey.bos.telegram_bot_template.util.BotUtil;
import rey.bos.telegram_bot_template.util.MessageUtil;

import static rey.bos.telegram_bot_template.bot.handler.impl.callback.CallBackCommand.MAKE_DECISION;
import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.AGREED_SOMETHING;
import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.DISAGREED_SOMETHING;

@Slf4j
@Component
public class MakeDecisionHandler extends BotHandlerDecision {

    private final BotUtil botUtil;

    public MakeDecisionHandler(MessageUtil messageUtil, BotUtil botUtil) {
        super(MAKE_DECISION, messageUtil);
        this.botUtil = botUtil;
    }

    @Override
    public boolean handleAccept(UserDto user, int messageId, String callBackData) {
        EditMessageText editMessageText = messageUtil.buildEditMessageText(
            user, messageId, AGREED_SOMETHING, callBackData
        );
        botUtil.executeMethod(editMessageText);
        return true;
    }

    @Override
    public boolean handleReject(UserDto user, int messageId, String callBackData) {
        EditMessageText editMessageText = messageUtil.buildEditMessageText(
            user, messageId, DISAGREED_SOMETHING, callBackData
        );
        botUtil.executeMethod(editMessageText);
        return true;
    }

    @Override
    public boolean support(Update update) {
        return supportCallbackCommand(update, command);
    }

}
