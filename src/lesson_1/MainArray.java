package lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainArray {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r = new Resume();
        ArrayStorage storage = new ArrayStorage();

        while (true) {
            System.out.print("Введите одну из команд - (save uuid surname | get uuid | delete uuid | size | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            if (params.length == 2 && (params[0].equals("save"))) {
                r.setUuid(params[1].intern());
            }
            if (params.length == 3) {
                r.setSurname(params[2].intern());
            }

            switch (params[0]) {
                case "save":
                    storage.save(r);
                    printStorage(storage);
                    break;
                case "get":
                    System.out.println(storage.get(r.getUuid()));
                    break;
                case "delete":
                    storage.delete(r.getUuid());
                    printStorage(storage);
                    break;
                case "size":
                    System.out.println(storage.size());
                    break;
                case "clear":
                    storage.clear();
                    printStorage(storage);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    public static void printStorage(ArrayStorage storage) {
        Resume[] resumes = storage.getAll();

        System.out.println("Печатаем содержимое хранилища!");
        System.out.println("----------------------------------------");
        for(Resume resume : resumes) {
            if (resume != null) {
                System.out.println("Резюме " + resume.getFirstName() + " " + resume.getSecondName() + " " + resume.getSurname());
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Сейчас в хранилище " + storage.size() + " элементов");
        System.out.println("----------------------------------------");
    }
}

