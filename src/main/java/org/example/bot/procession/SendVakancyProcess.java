package org.example.bot.procession;

import org.example.model.Vacancy;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class SendVakancyProcess {
    public static void sendMessage(Vacancy vacancy, Message message, TelegramLongPollingBot bot) {
        try {
            bot.execute(EditMessageText.builder()
                    .chatId(message.getChatId())
                    .messageId(message.getMessageId())
                    .text("Вакансия : " + vacancy.getCompanyName()+ "\nТип работы : " + vacancy.getKindOfWork()+ "\nЗарплата : " + vacancy.getSalary())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .keyboardRow(
                                    List.of(
                                        InlineKeyboardButton.builder().callbackData("getContactInfo").text("Откликнуться \uD83D\uDD90").build()
                            ))
                            .keyboardRow(
                                    List.of(
                                            InlineKeyboardButton.builder().callbackData("prev").text("◀\uFE0F").build(),
                                            InlineKeyboardButton.builder().callbackData("back").text("Назад").build(),
                                            InlineKeyboardButton.builder().callbackData("next").text("▶\uFE0F").build()
                                    )
                            ).build()
                    ).build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
