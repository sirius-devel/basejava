package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class ArrayStorage implements Storage {
    private static final  int STORAGE_LIMIT = 10000;
    private Resume[] storage;
    private int size;

    public ArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
        size = 0;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid().toString());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Ошибка -  вы пытаетесь обновить резюме, которого нет в базе.");
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid().toString()) == -1) {
            size++;
            if (size <= storage.length) {
                storage[size - 1] = r;
            } else {
                System.out.println("Ошибка - к сожалению, хранилище резюме полностью заполнено.");
            }
        } else {
            System.out.println("Ошибка -  вы пытаетесь сохранить резюме, которое уже есть в базе. Вызвана процедура обновления.");
            update(r);
        }
    }

    public Resume get(String uid) {
        int index = getIndex(uid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Ошибка - запрошено резюме, которого нет в базе.");
        }
        return null;
    }

    public void delete(String uid) {
        int index = getIndex(uid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Возникла ошибка - запрошенного для удаления резюме нет в базе.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uid) {
        for(int i = 0; i < size; i++) {
            if (storage[i].getUuid().toString().equals(uid)) {
                return i;
            }
        }
        return -1;
    }
}
