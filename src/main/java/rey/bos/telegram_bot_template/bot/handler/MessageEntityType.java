package rey.bos.telegram_bot_template.bot.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageEntityType {

    BOT_COMMAND("bot_command"),
    MENTION("mention");

    private final String description;

}
