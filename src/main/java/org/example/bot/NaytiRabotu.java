package org.example.bot;

import org.example.bot.handler.UpdateCallBackHnadler;
import org.example.bot.handler.UpdateMessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NaytiRabotu extends TelegramLongPollingBot {

    public NaytiRabotu(){
        super("6642648482:AAELGY3Tf9Wf6ZTM5yN9L_rd2EysR5C-Kzc");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            UpdateMessageHandler.hendleUpdateMessage(update.getMessage(), this);
        } else if (update.hasCallbackQuery()) {
            UpdateCallBackHnadler.HandleUpdateCallBackQuerry(update.getCallbackQuery(), this);
        }
    }

    @Override
    public String getBotUsername() {
        return "@rabota_uz_test_bot";
    }
}
