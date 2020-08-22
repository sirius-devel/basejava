package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> items;

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "list section elements must be not null");
        this.items = items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection section = (ListSection) o;
        return items.equals(section.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
