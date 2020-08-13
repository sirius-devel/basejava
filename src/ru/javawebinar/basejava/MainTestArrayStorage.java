package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.model.Resume;

import java.time.LocalDate;

public class MainTestArrayStorage {
    //static final Storage ARRAY_STORAGE = new ArrayStorage();
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    public static void main(String[] args) {

        Resume r1 = new Resume();
        r1.setUuid("58e0a7d7-eebc-11d8-9669-0800200c9a61");
        Resume r2 = new Resume();
        r2.setUuid("58e0a7d7-eebc-11d8-9669-0800200c9a65");
        Resume r3 = new Resume();
        r3.setUuid("58e0a7d7-eebc-11d8-9669-0800200c9a62");
        Resume r4 = new Resume();
        r4.setUuid("58e0a7d7-eebc-11d8-9669-0800200c9a63");
        Resume r5 = new Resume();
        r5.setUuid("58e0a7d7-eebc-11d8-9669-0800200c9a64");
        Resume r6 = new Resume();
        r6.setUuid("58e0a7d7-eebc-11d8-9669-0800200c9a66");
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
