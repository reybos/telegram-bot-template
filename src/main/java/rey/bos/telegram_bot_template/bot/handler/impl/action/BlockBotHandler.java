package rey.bos.telegram_bot_template.bot.handler.impl.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.bot.handler.ChatMemberStatus;
import rey.bos.telegram_bot_template.shared.dto.UserDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class BlockBotHandler extends BotHandler {

    @Override
    public boolean handle(Update update, UserDto user) {
        logCall(user.getTelegramId(), "block bot", "");
        return true;
    }

    @Override
    public boolean support(Update update) {
        return update.hasMyChatMember()
            && update.getMyChatMember().getNewChatMember().getStatus().equals(ChatMemberStatus.KICKED.getValue());
    }

}
