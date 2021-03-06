package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.util.List;

public class MainTestArrayStorage {
    //static final Storage ARRAY_STORAGE = new ArrayStorage();
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    public static void main(String[] args) {

        Resume resume1 = new Resume("58e0a7d7-eebc-11d8-9669-0800200c9a61", "name1");
        Resume resume2 = new Resume("58e0a7d7-eebc-11d8-9669-0800200c9a65", "name2");
        Resume resume3 = new Resume("58e0a7d7-eebc-11d8-9669-0800200c9a62", "name3");
        Resume resume4 = new Resume("58e0a7d7-eebc-11d8-9669-0800200c9a63", "name4");
        Resume resume5 = new Resume("58e0a7d7-eebc-11d8-9669-0800200c9a64", "name5");
        Resume resume6 = new Resume("58e0a7d7-eebc-11d8-9669-0800200c9a66", "name6");
        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);
        ARRAY_STORAGE.save(resume4);
        ARRAY_STORAGE.save(resume5);
        printStorage();
        Resume resume = ARRAY_STORAGE.get(resume2.getUuid());
        System.out.println("Запрашиваем из хранилище резюме " + resume);
        if (resume == null) {
            System.out.println("Не смогли найти резюме с uuid=" + resume2.getUuid() + " в хранилище.");
        } else {
            System.out.println("Получили резюме " + resume);
        }
        ARRAY_STORAGE.delete(resume3.getUuid());
        System.out.println("Удаляем из хранилище резюме " + resume3);
        printStorage();
        System.out.println("Обновляем резюме " + resume5);
        ARRAY_STORAGE.update(resume5);
        printStorage();
        System.out.println("Обновляем резюме " + resume6);
        ARRAY_STORAGE.update(resume6);
        printStorage();
        System.out.println("Очищаем хранилище");
        ARRAY_STORAGE.clear();
        printStorage();
    }

    public static void printStorage() {
        List<Resume> resumes = ARRAY_STORAGE.getAllSorted();

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
