package rey.bos.telegram_bot_template.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import rey.bos.telegram_bot_template.bot.handler.BotHandler;
import rey.bos.telegram_bot_template.bot.handler.impl.command.MenuCommand;
import rey.bos.telegram_bot_template.config.TelegramBotProperty;
import rey.bos.telegram_bot_template.shared.dto.UserDto;
import rey.bos.telegram_bot_template.shared.mapper.UserDtoMapper;
import rey.bos.telegram_bot_template.util.BotUtil;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot implements LongPollingSingleThreadUpdateConsumer, SpringLongPollingBot {

    private final TelegramClient telegramClient;
    private final TelegramBotProperty telegramBotProperty;
    private final List<BotHandler> handlers;
    private final BotUtil botUtil;
    private final UserDtoMapper userDtoMapper;

    public TelegramBot(
        TelegramClient telegramClient, TelegramBotProperty telegramBotProperty, List<BotHandler> handlers,
        BotUtil botUtil, UserDtoMapper userDtoMapper
    ) {
        this.telegramClient = telegramClient;
        this.telegramBotProperty = telegramBotProperty;
        this.handlers = handlers;
        this.botUtil = botUtil;
        this.userDtoMapper = userDtoMapper;
        setCommands();
    }

    private void setCommands() {
        List<BotCommand> commands = new ArrayList<>();
        for (MenuCommand menuCommand : MenuCommand.getCommandsForMenu()) {
            commands.add(new BotCommand(menuCommand.getCommand(), menuCommand.getDescription()));
        }
        SetMyCommands setMyCommands = new SetMyCommands(commands);
        try {
            telegramClient.execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error("Can't execute command", e);
        }
    }

    @Override
    public void consume(Update update) {
        UserDto user = getOrCreateUser(update);
        boolean handled = false;
        for (BotHandler handler : handlers) {
            if (handler.support(update)) {
                handled = handler.handle(update, user);
            }
        }
        if (!handled) {
            botUtil.sendSomethingWentMessage(user.getTelegramId(), user.getLanguageCode());
        }
    }

    public UserDto getOrCreateUser(Update update) {
        org.telegram.telegrambots.meta.api.objects.User user;
        if (update.hasMyChatMember()) {
            user = update.getMyChatMember().getFrom();
        } else if (update.hasCallbackQuery()) {
            user = update.getCallbackQuery().getFrom();
        } else {
            user = update.getMessage().getFrom();
        }
        return userDtoMapper.map(user);
    }

    @Override
    public String getBotToken() {
        return telegramBotProperty.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        log.info("Registered bot running state is: " + botSession.isRunning());
    }

}