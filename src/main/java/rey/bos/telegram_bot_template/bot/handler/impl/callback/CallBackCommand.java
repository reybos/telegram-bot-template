package rey.bos.telegram_bot_template.bot.handler.impl.callback;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CallBackCommand {

    MAKE_DECISION("make_decision-"),

    CONFIRM("-yes"),
    REJECT("-no");

    private final String command;

}
