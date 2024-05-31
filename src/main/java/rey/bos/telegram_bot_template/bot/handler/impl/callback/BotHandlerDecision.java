package rey.bos.telegram_bot_template.bot.handler.impl.callback;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.shared.dto.UserDto;
import rey.bos.telegram_bot_template.util.MessageUtil;

import static rey.bos.telegram_bot_template.bot.handler.impl.callback.CallBackCommand.CONFIRM;

@AllArgsConstructor
public abstract class BotHandlerDecision extends BotHandler {

    public final CallBackCommand command;
    public final MessageUtil messageUtil;

    @Override
    public boolean handle(Update update, UserDto user) {
        CallbackQuery query = update.getCallbackQuery();
        String callBackData = query.getData();
        int messageId = query.getMessage().getMessageId();
        logCall(user.getTelegramId(), command.getCommand(), callBackData);

        return callBackData.endsWith(CONFIRM.getCommand())
            ? handleAccept(user, messageId, callBackData)
            : handleReject(user, messageId, callBackData);
    }

    public abstract boolean handleAccept(UserDto user, int messageId, String callBackData);

    public abstract boolean handleReject(UserDto user, int messageId, String callBackData);

}
