package org.example.bot.procession;

import org.example.bot.state.State;
import org.example.bot.state.StateRepo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class AddVacancyProcesion {
    public static void processCompanyName(Message message, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder().text(
                    "Введите название компании"
            ).messageId(message.getMessageId()).chatId(message.getChatId()).build());
            StateRepo.ADD_VACANCY_STATE.put(message.getChatId(), State.Vacancy_Company_Name);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void processKindOfWork(Message message, TelegramLongPollingBot bot){
        try {
            bot.execute(SendMessage.builder().text("Введите тип работы")
                    .chatId(message.getChatId()).build());
            StateRepo.ADD_VACANCY_STATE.put(message.getChatId(), State.Kind_Of_Work);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processSalary(Message message, TelegramLongPollingBot bot){
        try {
            bot.execute(SendMessage.builder().text("Введите зарплату")
                    .chatId(message.getChatId()).build());
            StateRepo.ADD_VACANCY_STATE.put(message.getChatId(), State.Salary);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void procesContactUs(Long chatId, TelegramLongPollingBot bot) {
        try {
            bot.execute(
                    SendMessage.builder()
                            .chatId(chatId)
                            .text("Оставьте контактные данные компании")
                            .build()
            );
            StateRepo.ADD_VACANCY_STATE.put(chatId, State.Сontact_Us);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
