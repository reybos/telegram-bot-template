package rey.bos.telegram_bot_template.bot.handler.impl.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.bot.handler.ChatMemberStatus;
import rey.bos.telegram_bot_template.shared.dto.UserDto;

@Component
@Slf4j
public class BeforeStartHandler extends BotHandler {

    @Override
    public boolean handle(Update update, UserDto user) {
        logCall(user.getTelegramId(), "Received a request before the /start command", "");
        return true;
    }

    @Override
    public boolean support(Update update) {
        return update.hasMyChatMember()
            && update.getMyChatMember().getNewChatMember().getStatus().equals(ChatMemberStatus.MEMBER.getValue());
    }

}
