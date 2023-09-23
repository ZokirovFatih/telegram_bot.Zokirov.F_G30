package org.example.bot.handler;

import org.example.bot.handler.message.CommandHandler;
import org.example.bot.procession.*;
import org.example.service.VacancyService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UpdateCallBackHnadler {
    public static void HandleUpdateCallBackQuerry(CallbackQuery callbackQuery, TelegramLongPollingBot bot) {
        switch (callbackQuery.getData()) {
            case "addVacancy" -> AddVacancyProcesion.processCompanyName(callbackQuery.getMessage(), bot);
            case "vacancy" -> {
                if (VacancyService.vacancyList.isEmpty()) {
                    try {
                        bot.execute(AnswerCallbackQuery.builder()
                                .text("Пока нет доступных вакансий")
                                .callbackQueryId(callbackQuery.getId())
                                .showAlert(true)
                                .build()
                        );
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else
                    SendVakancyProcess.sendMessage(VacancyService.vacancyList.get(VacancyService.index), callbackQuery.getMessage(), bot);
            }
            case "getContactInfo" -> {
                SendContactInfo.process(VacancyService.contactInfoList.get(VacancyService.index), callbackQuery.getMessage(), bot);
                CommandHandler.handleStartComand(callbackQuery.getMessage(), bot);
            }
            case "next" -> NextButtonProcess.process(callbackQuery, bot);
            case "prev" -> PrevButtonState.process(callbackQuery, bot);
            case "back" -> CommandHandler.handleStateCommandEdition(callbackQuery.getMessage(), bot);
        }
    }
}
