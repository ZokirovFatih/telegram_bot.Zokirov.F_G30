package org.example.bot.handler;

import org.example.bot.handler.message.MessageTextHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UpdateMessageHandler {
    public static void hendleUpdateMessage(Message message, TelegramLongPollingBot bot) {
        if (message.hasText()){
            MessageTextHandler.handlerMessageText(message, bot);
        }
    }
}
