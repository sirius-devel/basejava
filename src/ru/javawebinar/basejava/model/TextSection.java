package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    protected final String content;
    private static final long serialVersionUID = 1L;

    public TextSection(String text) {
        Objects.requireNonNull(text, "content must be not null");
        this.content = text;
    }

    public String getText() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection tb = (TextSection) o;

        return content.equals(tb.content);

    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
