package rey.bos.telegram_bot_template.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LanguageCode {

    RU("ru"),
    EN("en");

    private final String value;

}
