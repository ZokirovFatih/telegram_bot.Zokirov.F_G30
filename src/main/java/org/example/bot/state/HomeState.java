package org.example.bot.state;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class HomeState {
    public static final InlineKeyboardMarkup INLINE_MARKUP_HOME_STATE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().callbackData("vacancy").text("Вакансии").build(),
                    InlineKeyboardButton.builder().callbackData("addVacancy").text("Добавить вакансию").build()
            )).build();
}
