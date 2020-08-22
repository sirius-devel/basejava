package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "start date must be not null");
        Objects.requireNonNull(endDate, "end date must be not null");
        Objects.requireNonNull(title, "title must be not null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "organization (" +
                "link=" + homePage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", subtitle='" + title + '\'' +
                ", text='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization org = (Organization) o;

        if (!homePage.equals(org.homePage)) return false;
        if (!startDate.equals(org.startDate)) return false;
        if (!endDate.equals(org.endDate)) return false;
        if (!title.equals(org.title)) return false;
        return Objects.equals(description, org.description);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
