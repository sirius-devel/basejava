package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainArray {
    //private final static  Storage ARRAY_STORAGE = new ArrayStorage();
    private final static  Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Resume resume;
            System.out.print("Введите одну из команд - (list | save uuid fullName| get uuid | update uuid fullName | delete uuid | size | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            if (params.length == 2) {
                resume = new Resume(params[1].intern(), "dummy");
            } else if (params.length == 3) {
                resume = new Resume(params[1].intern(), params[2].intern());
            } else {
                    resume = new Resume();
            }

            switch (params[0]) {
                case "list":
                    printStorage();
                    break;
                case "save":
                    ARRAY_STORAGE.save(resume);
                    printStorage();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(resume.getUuid()));
                    break;
                case "update":
                    ARRAY_STORAGE.update(resume);
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(resume.getUuid());
                    printStorage();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printStorage();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    public static void printStorage() {
        List<Resume> resumes = ARRAY_STORAGE.getAllSorted();

        System.out.println("Печатаем содержимое хранилища!");
        System.out.println("----------------------------------------");
        for(Resume resume : resumes) {
            System.out.println(resume);
        }
        System.out.println("----------------------------------------");
        System.out.println("Сейчас в хранилище " + ARRAY_STORAGE.size() + " элементов");
        System.out.println("----------------------------------------");
    }
}

