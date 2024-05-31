package rey.bos.telegram_bot_template.dictionary.impl;

import org.springframework.stereotype.Component;
import rey.bos.telegram_bot_template.dictionary.Dictionary;
import rey.bos.telegram_bot_template.dictionary.DictionaryKey;
import rey.bos.telegram_bot_template.shared.dto.LanguageCode;

import java.util.HashMap;
import java.util.Map;

import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.*;

@Component
public class DictionaryRu implements Dictionary {

    private final LanguageCode languageCode;
    private final Map<DictionaryKey, String> dictionary;

    public DictionaryRu() {
        languageCode = LanguageCode.RU;
        dictionary = new HashMap<>();
        addValues();
    }

    @Override
    public boolean isSuitable(LanguageCode languageCode) {
        return languageCode == this.languageCode;
    }

    @Override
    public String get(DictionaryKey key) {
        return dictionary.get(key);
    }

    private void addValues() {
        dictionary.put(SOMETHING_WENT_WRONG, """
            Что-то пошло не так 😿 Если проблемы сохраняются, напишите создателю бота @reybos, он попробует помочь.
            """
        );
        dictionary.put(CONFIRM_MSG, """
            Да ✅"""
        );
        dictionary.put(REJECT_MSG, """
            Нет ❌"""
        );
        dictionary.put(GREETING_FOR_START, """
            Это приветственное сообщение на нажатие кнопки "Start"
            """
        );
        dictionary.put(MAKE_DECISION_MESSAGE, """
            В этом сообщении можно предложить с чем-то согласиться

            Вы согласны?
            """
        );
        dictionary.put(AGREED_SOMETHING, """
            Вы согласились на что-то

            callBackData = %s
            """
        );
        dictionary.put(DISAGREED_SOMETHING, """
            Вы не согласились на что-то

            callBackData = %s
            """
        );
        dictionary.put(ECHO_MESSAGE, """
            Вы прислали боту слудующее сообщение:
            %s
            """
        );
        dictionary.put(HELP_COMMAND_MESSAGE, """
            Тут можно написать что умеет бот
            """
        );
        dictionary.put(UNSUPPORTED_COMMAND, """
            Команда не поддерживается, список доступных команд можно посмотреть в меню или вызвав команду /help
            """
        );
    }

}
