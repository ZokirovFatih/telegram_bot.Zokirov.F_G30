package org.example.service;
import org.example.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class VacancyService {
    public static int index = 0;
    public static List<Vacancy> vacancyList = new ArrayList<>();
    public static List<String> contactInfoList = new ArrayList<>();

    public static boolean addVacancy(Vacancy vacancy){
        vacancyList.add(new Vacancy(vacancy.getCompanyName(), vacancy.getKindOfWork(), vacancy.getSalary()));
        System.out.println(vacancy.getCompanyName()+ "\n" + vacancy.getKindOfWork()+ "\n" + vacancy.getSalary());
        return true;
    }
    public static boolean addContactInfo(String contactInfo){
        contactInfoList.add(contactInfo);
        System.out.println(contactInfo + "\n-----------------------------------");
        return true;
    }


}
