package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {
    private final  List<Organization> organizations;

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must be not null");
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection section = (OrganizationSection) o;
        return organizations.equals(section.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }
}