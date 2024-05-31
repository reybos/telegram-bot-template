package rey.bos.telegram_bot_template.dictionary;

import rey.bos.telegram_bot_template.shared.dto.LanguageCode;

public interface Dictionary {

    boolean isSuitable(LanguageCode languageCode);

    String get(DictionaryKey key);

}
