package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends Section {
    protected final String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "content must not be null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection tb = (TextSection) o;

        return text.equals(tb.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
