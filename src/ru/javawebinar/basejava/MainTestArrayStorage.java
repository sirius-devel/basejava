package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.model.Resume;

import java.time.LocalDate;

public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();
    public static void main(String[] args) {

        Resume r1 = new Resume();
        Resume r2 = new Resume();
        Resume r3 = new Resume();
        Resume r4 = new Resume();
        Resume r5 = new Resume();
        Resume r6 = new Resume();
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        printStorage();
        Resume r = ARRAY_STORAGE.get(r2.getUuid().toString());
        System.out.println("Запрашиваем из хранилище резюме " + r);
        if (r == null) {
            System.out.println("Не смогли найти резюме с uuid=" + r2.getUuid().toString() + " в хранилище.");
        } else {
            System.out.println("Получили резюме " + r);
        }
        ARRAY_STORAGE.delete(r3.getUuid().toString());
        System.out.println("Удаляем из хранилище резюме " + r3);
        printStorage();
        System.out.println("Обновляем резюме " + r5);
        ARRAY_STORAGE.update(r5);
        printStorage();
        System.out.println("Обновляем резюме " + r6);
        ARRAY_STORAGE.update(r6);
        printStorage();
        System.out.println("Очищаем хранилище");
        ARRAY_STORAGE.clear();
        printStorage();
    }

    public static void printStorage() {
        Resume[] resumes = ARRAY_STORAGE.getAll();

        System.out.println("Печатаем содержимое хранилища!");
        System.out.println("----------------------------------------");
        for(Resume resume : resumes) {
            if (resume != null) {
                System.out.println(resume);
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Сейчас в хранилище " + ARRAY_STORAGE.size() + " элементов");
        System.out.println("----------------------------------------");
    }
}
