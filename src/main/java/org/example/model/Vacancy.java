package org.example.model;

public class Vacancy {
    private String companyName;
    private String KindOfWork;
    private String Salary;

    public Vacancy() {

    }

    public Vacancy(String companyName, String kindOfWork, String salary) {
        this.companyName = companyName;
        KindOfWork = kindOfWork;
        Salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKindOfWork() {
        return KindOfWork;
    }

    public void setKindOfWork(String kindOfWork) {
        KindOfWork = kindOfWork;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return  companyName +
                "\n Тип работы: " + KindOfWork +
                "\n Зарпоата: " + Salary;
    }
}
