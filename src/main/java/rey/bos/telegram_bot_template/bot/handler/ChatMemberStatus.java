package rey.bos.telegram_bot_template.bot.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatMemberStatus {

    KICKED("kicked"),
    MEMBER("member");

    private final String value;

}
