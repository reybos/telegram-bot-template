package rey.bos.telegram_bot_template.dictionary.impl;

import org.springframework.stereotype.Component;
import rey.bos.telegram_bot_template.dictionary.Dictionary;
import rey.bos.telegram_bot_template.dictionary.DictionaryKey;
import rey.bos.telegram_bot_template.shared.dto.LanguageCode;

import java.util.HashMap;
import java.util.Map;

import static rey.bos.telegram_bot_template.dictionary.DictionaryKey.*;

@Component
public class DictionaryEn implements Dictionary {

    private final LanguageCode languageCode;
    private final Map<DictionaryKey, String> dictionary;

    public DictionaryEn() {
        languageCode = LanguageCode.EN;
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
            Something went wrong 😿 If problems persist, write to the creator of the bot @reybos, he will try to help.
            """
        );
        dictionary.put(CONFIRM_MSG, """
            Yes ✅"""
        );
        dictionary.put(REJECT_MSG, """
            No ❌"""
        );
        dictionary.put(GREETING_FOR_START, """
            This is a welcome message for pressing the "Start" button
            """
        );
        dictionary.put(MAKE_DECISION_MESSAGE, """
            In this message, you can suggest agreeing with something
            
            Do you agree?
            """
        );
        dictionary.put(AGREED_SOMETHING, """
            Have you agreed to something
            
            callBackData = %s
            """
        );
        dictionary.put(DISAGREED_SOMETHING, """
            You didn't agree to something
            
            callBackData = %s
            """
        );
        dictionary.put(ECHO_MESSAGE, """
            You have sent the following message to the bot:
            %s
            """
        );
        dictionary.put(HELP_COMMAND_MESSAGE, """
            Here you can write what the bot can do
            """
        );
        dictionary.put(UNSUPPORTED_COMMAND, """
            The command is not supported, the list of available commands can be viewed in the menu or by calling the /help command
            """
        );
    }
}
