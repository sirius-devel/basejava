package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.UUID;

public class Resume {
    private UUID uuid;
    private LocalDate birthday;
    private char gender;
    private String surname;
    private String secondName;
    private String firstName;
    private String profession;
    private String skills;

    public Resume() {
        uuid = UUID.randomUUID();
    }

    public Resume(LocalDate birthday, char gender,  String firstName, String secondName,
           String surname, String profession, String skills) {
        uuid = UUID.randomUUID();
        this.birthday = birthday;
        this.gender = gender;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.profession = profession;
        this.skills = skills;
    }

    public String getUuid() {
        return uuid.toString();
    }

    public void setUuid(String uuid) {
        try {
            this.uuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            this.uuid = UUID.randomUUID();
            System.out.println("Был задан неверный формат уникального идентификатора uuid, поэтому был сгенерирован " +
                    "случайный uid=" + this.uuid);
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String toString() {
        return "Резюме " + firstName + " " + secondName + " " + surname;
    }
}
