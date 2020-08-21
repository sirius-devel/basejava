package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class MarkedListSection extends Section {
    private List<String> elements;

    public MarkedListSection(List<String> elements) {
        Objects.requireNonNull(elements, "section elements must not be null");
        this.elements = elements;
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElements(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkedListSection section = (MarkedListSection) o;

        return elements.equals(section.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }
}
