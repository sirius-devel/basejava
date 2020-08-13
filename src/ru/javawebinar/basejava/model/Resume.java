package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.UUID;

public class Resume {
    private UUID uuid;

    public Resume() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        try {
            this.uuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            this.uuid = UUID.randomUUID();
            System.out.println("Был задан неверный формат уникального идентификатора uuid, поэтому был сгенерирован " +
                    "случайный uid=" + this.uuid);
        }
    }

    public String toString() {
        return uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
