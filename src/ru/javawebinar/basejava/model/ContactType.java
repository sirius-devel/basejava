package ru.javawebinar.basejava.model;

public enum ContactType {
    MOBILE_PHONE("Сотовый тел."),
    HOME_PHONE("Домашний тел."),
    SKYPE("Скайп"),
    EMAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
