package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class OrganizationListSection extends Section {
    private List<Organization> elements;

    public OrganizationListSection(List<Organization> elements) {
        Objects.requireNonNull(elements, "section elements must not be null");
        this.elements = elements;
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public List<Organization> getElements() {
        return elements;
    }

    public void setElements(List<Organization> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationListSection section = (OrganizationListSection) o;

        return elements.equals(section.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }
}
