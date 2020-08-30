package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    protected String content;
    private static final long serialVersionUID = 1L;
    public static final TextSection EMPTY = new TextSection("");

    public TextSection() {
    }

    public TextSection(String text) {
        this.content = text;
        Objects.requireNonNull(content, "content must be not null");
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
        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
