package rey.bos.telegram_bot_template.bot.handler.impl.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum MenuCommand {

    MENU_COMMAND_START("/start", ""),
    MENU_COMMAND_MAKE_DECISION("/decision", """
        make decision ⚙️"""),
    MENU_COMMAND_HELP("/help", """
        help ❔️""");

    private final String command;
    private final String description;

    public static List<MenuCommand> getCommandsForMenu() {
        return List.of(
            MENU_COMMAND_MAKE_DECISION,
            MENU_COMMAND_HELP
        );
    }

}
