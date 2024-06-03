package ru.rinzler.library.Models;

public class Person {
    private int id;
    private String firstName;
    private String surName;
    private String patronymic;
    private int year;

    public Person(){}

    public Person (int id, String firstName, String surName, String patronymic, int year){
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.patronymic = patronymic;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
