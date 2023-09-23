package org.example.bot.procession;

import org.example.service.VacancyService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class NextButtonProcess {
    public static void process(CallbackQuery callbackQuery, TelegramLongPollingBot bot) {
        if (VacancyService.index == VacancyService.vacancyList.size()-1) {
            try {
                bot.execute(AnswerCallbackQuery.builder()
                        .text("Конец списка")
                        .showAlert(true)
                        .callbackQueryId(callbackQuery.getId())
                        .build()
                );
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            VacancyService.index++;
            SendVakancyProcess.sendMessage(VacancyService.vacancyList.get(VacancyService.index), callbackQuery.getMessage(), bot);
        }
    }
}
