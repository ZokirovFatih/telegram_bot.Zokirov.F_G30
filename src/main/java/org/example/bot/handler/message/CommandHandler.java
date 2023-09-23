package org.example.bot.handler.message;

import org.example.bot.enums.Comand;
import org.example.bot.state.HomeState;
import org.example.service.VacancyService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CommandHandler {
    public static void handleComand(Message message, TelegramLongPollingBot bot) {
        switch (Comand.of(message.getText())){
            case COMAND_START -> handleStartComand(message, bot);
            case COMAND_HELP -> handleHelpComand(message, bot);
            case COMAND_SETTINGS -> handleSettingsComand(message, bot);
        }
    }

    private static void handleSettingsComand(Message message, TelegramLongPollingBot bot) {
    }

    private static void handleHelpComand(Message message, TelegramLongPollingBot bot) {
        try {
            bot.execute(SendMessage.builder().chatId(message.getChatId()).text("Етот бот для поиска работы в меню (Добавить вакансию) вы добовляйте открытою работу" +
                    " для людей а в разделе (Доступные вакнсии) можите найти работу или подработку")
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void handleStartComand(Message message, TelegramLongPollingBot bot){
        VacancyService.index = 0;
        try {
            bot.execute(SendMessage.builder()
                    .chatId(message.getChatId())
                    .text("Добро пожаловать, здесь вы найдёте себе хорошую работу или подработку \uD83D\uDE0A")
                    .replyMarkup(HomeState.INLINE_MARKUP_HOME_STATE)
                    .build());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public static void handleStateCommandEdition(Message message,TelegramLongPollingBot bot){
        VacancyService.index = 0;
        try {
            bot.execute(EditMessageText.builder()
                    .chatId(message.getChatId())
                    .messageId(message.getMessageId())
                    .text("Добро пожаловать здесь вы найдёте себе работу или подработку мечты \uD83E\uDD29")
                    .replyMarkup(HomeState.INLINE_MARKUP_HOME_STATE)
                    .build());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
