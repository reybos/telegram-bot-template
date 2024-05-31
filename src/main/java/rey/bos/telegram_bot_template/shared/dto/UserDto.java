package rey.bos.telegram_bot_template.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private long telegramId;

    private String userName;

    private String firstName;

    private LanguageCode languageCode;

}
