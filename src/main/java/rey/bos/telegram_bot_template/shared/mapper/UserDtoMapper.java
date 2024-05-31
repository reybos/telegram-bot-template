package rey.bos.telegram_bot_template.shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rey.bos.telegram_bot_template.shared.dto.LanguageCode;
import rey.bos.telegram_bot_template.shared.dto.UserDto;

@Mapper(componentModel="spring")
public abstract class UserDtoMapper {

    @Mapping(target = "telegramId", source = "id")
    public abstract UserDto map(org.telegram.telegrambots.meta.api.objects.User user);

    LanguageCode map(String code) {
        for (LanguageCode languageCode : LanguageCode.values()) {
            if (languageCode.getValue().equals(code)) {
                return languageCode;
            }
        }
        return LanguageCode.EN;
    }

}
