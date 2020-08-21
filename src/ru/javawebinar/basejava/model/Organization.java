package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final Link link;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String subTitle;
    private final String text;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String subTitle, String text) {
        Objects.requireNonNull(startDate, "start date must not be null");
        Objects.requireNonNull(endDate, "end date must not be null");
        Objects.requireNonNull(subTitle, "subtitle must not be null");
        this.link = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.subTitle = subTitle;
        this.text = text;
    }

    @Override
    public String toString() {
        return "organization (" +
                "link=" + link +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", subtitle='" + subTitle + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization org = (Organization) o;

        if (!link.equals(org.link)) return false;
        if (!startDate.equals(org.startDate)) return false;
        if (!endDate.equals(org.endDate)) return false;
        if (!subTitle.equals(org.subTitle)) return false;
        return Objects.equals(text, org.text);
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + subTitle.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
