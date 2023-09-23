package org.example.bot.handler.message;

import org.example.bot.procession.AddVacancyProcesion;
import org.example.bot.state.State;
import org.example.bot.state.StateRepo;
import org.example.model.Vacancy;
import org.example.service.VacancyService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageTextHandler {

    private static Vacancy vacancy = new Vacancy();
    public static void handlerMessageText(Message message, TelegramLongPollingBot bot) {
        if (message.isCommand()){
            CommandHandler.handleComand(message, bot);
        }else {
            switch (StateRepo.ADD_VACANCY_STATE.get(message.getChatId())){
                case Vacancy_Company_Name -> handleCompanyName(message, bot);
                case Kind_Of_Work -> handleKindOfWork(message, bot);
                case Salary -> handleSalary(message, bot);
                case Сontact_Us -> handleСontactUs(message, bot);
                case Home -> handleHome(message, bot);
            }
        }
    }

    public static void handleCompanyName(Message message, TelegramLongPollingBot bot){
        StateRepo.ADD_VACANCY_STATE.put(message.getChatId(),State.Kind_Of_Work);
        String companyName = message.getText();
        vacancy.setCompanyName(companyName);
        finalVacancyInfo(vacancy);
        AddVacancyProcesion.processKindOfWork(message, bot);

    }

    public static void handleKindOfWork(Message message, TelegramLongPollingBot bot){
        StateRepo.ADD_VACANCY_STATE.put(message.getChatId(), State.Salary);
        String kindOfWork = message.getText();
        vacancy.setKindOfWork(kindOfWork);
        finalVacancyInfo(vacancy);
        AddVacancyProcesion.processSalary(message, bot);
    }

    public static void handleSalary(Message message, TelegramLongPollingBot bot){
        StateRepo.ADD_VACANCY_STATE.put(message.getChatId(), State.Сontact_Us);
        String salary = message.getText();
        vacancy.setSalary(salary);
        finalVacancyInfo(vacancy);
        AddVacancyProcesion.procesContactUs(message.getChatId(), bot);
    }

    public static void handleСontactUs(Message message, TelegramLongPollingBot bot){
        VacancyService.addContactInfo(message.getText());
       handleHome(message, bot);
    }

   public static void handleHome(Message message, TelegramLongPollingBot bot){
        StateRepo.ADD_VACANCY_STATE.put(message.getChatId(), State.Home);
        CommandHandler.handleStartComand(message, bot);
       vacancy.setCompanyName(null);
       vacancy.setKindOfWork(null);
       vacancy.setSalary(null);
    }

    private static void finalVacancyInfo(Vacancy vacancy){
        if (vacancy.getCompanyName()!=null){
            if (vacancy.getKindOfWork()!=null){
                if (vacancy.getSalary()!=null){
                    VacancyService.addVacancy(new Vacancy(vacancy.getCompanyName(), vacancy.getKindOfWork(), vacancy.getSalary()));
                }
            }
        }
    }
}
