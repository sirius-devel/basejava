package lesson_1;

import java.time.LocalDate;

public class MainTestArrayStorage {
    public static void main(String[] args) {

        Resume r1 = new Resume(LocalDate.of(1990, 12, 22), 'М',  "Александр", "Александрович",
                "Петров", "сварщик", "третий разряд");
        Resume r2 = new Resume(LocalDate.of(1985, 05, 10), 'Ж',  "Диана", "Алексеевна",
                "Пуговкина", "повар", "первая категория");
        Resume r3 = new Resume(LocalDate.of(1992, 04, 07), 'М',  "Василий", "Викторович",
                "Пушков", "программист", "middle c++");
        Resume r4 = new Resume(LocalDate.of(1994, 11, 03), 'Ж',  "Наталья", "Михайловна",
                "Кузнецова", "программист", "middle java");
        Resume r5 = new Resume(LocalDate.of(1995, 12, 22), 'М',  "Николай", "Игоревич",
                "Скворцов", "менеджер по продажам", "региональная продажа сельхозтехники");
        ArrayStorage storage = new ArrayStorage();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        storage.save(r4);
        storage.save(r5);
        printStorage(storage);
        Resume r = storage.get(r2.getUuid().toString());
        System.out.println("Запрашиваем из хранилище резюме " + r.getSurname());
        if (r == null) {
            System.out.println("Не смогли найти резюме с uuid=" + r2.getUuid().toString() + " в хранилище.");
        } else {
            System.out.println("Получили резюме " + r.getFirstName() + " " + r.getSecondName() + " " + r.getSurname());
        }
        storage.delete(r3.getUuid().toString());
        System.out.println("Удаляем из хранилище резюме " + r3.getSurname());
        printStorage(storage);
        System.out.println("Очищаем хранилище");
        storage.clear();
        printStorage(storage);
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
