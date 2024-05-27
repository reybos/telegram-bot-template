package rey.bos.telegram_bot_template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class MainConfig {

    @Bean
    public TelegramClient telegramClient(TelegramBotProperty telegramBotProperty) {
        return new OkHttpTelegramClient(telegramBotProperty.getToken());
    }

}
