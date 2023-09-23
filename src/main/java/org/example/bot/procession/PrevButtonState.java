package org.example.bot.procession;

import org.example.service.VacancyService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PrevButtonState {
    public static void process(CallbackQuery callbackQuery, TelegramLongPollingBot bot){
        if (VacancyService.index == 0){
            try {
                bot.execute(AnswerCallbackQuery.builder()
                        .text("Начало списка")
                        .showAlert(true)
                        .callbackQueryId(callbackQuery.getId())
                        .build()
                );
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else {
            VacancyService.index--;
            SendVakancyProcess.sendMessage(VacancyService.vacancyList.get(VacancyService.index), callbackQuery.getMessage(), bot);
        }
    }
}
