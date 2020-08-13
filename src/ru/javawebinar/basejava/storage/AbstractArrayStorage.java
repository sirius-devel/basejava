package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage;
    protected int size;

    public AbstractArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Ошибка -  вы пытаетесь обновить резюме" + resume.getUuid() + ", которого нет в базе.");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            size++;
            if (size <= storage.length) {
                insertElement(resume, index);
                //storage[size - 1] = resume;
            } else {
                System.out.println("Ошибка - к сожалению, хранилище резюме полностью заполнено.");
            }
        } else {
            System.out.println("Ошибка -  вы пытаетесь сохранить резюме" + resume.getUuid() + ", которое уже есть в базе. Вызвана процедура обновления.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не существует");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Возникла ошибка - запрошенного для удаления резюме" + uuid + " нет в базе.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
