package org.example.bot.procession;

import org.example.service.VacancyService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendContactInfo {
    public static void process(String msj, Message message, TelegramLongPollingBot bot){
        try {
            bot.execute(EditMessageText.builder().messageId(message.getMessageId()).text("Вакансия : " + VacancyService.vacancyList.get(VacancyService.index) +
                    "\nТел для связи : " + msj).chatId(message.getChatId()).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
