package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainArray {
    private final static  Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Resume r = new Resume();
            System.out.print("Введите одну из команд - (list | save uuid | get uuid | update uuid | delete uuid | size | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            if (params.length == 2) {
                r.setUuid(params[1].intern());
            }

            switch (params[0]) {
                case "list":
                    printStorage();
                    break;
                case "save":
                    ARRAY_STORAGE.save(r);
                    printStorage();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(r.getUuid().toString()));
                    break;
                case "update":
                    ARRAY_STORAGE.update(r);
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(r.getUuid().toString());
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
        Resume[] resumes = ARRAY_STORAGE.getAll();

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

