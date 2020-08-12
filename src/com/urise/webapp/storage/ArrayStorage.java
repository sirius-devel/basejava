package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage;
    private int size;

    public ArrayStorage() {
        storage = new Resume[10000];
        size = 0;
    }

    public void save(Resume r) {
        if (getResumeIndex(r.getUuid().toString()) == -1) {
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
        int index = getResumeIndex(uid);
        Resume result = null;
        if (index != -1) {
            result = storage[index];
        } else {
            System.out.println("Ошибка - запрошено резюме, которого нет в базе.");
        }
        return result;
    }

    public void update(Resume r) {
        int index = getResumeIndex(r.getUuid().toString());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Ошибка -  вы пытаетесь обновить резюме, которого нет в базе.");
        }
    }

    public void delete(String uid) {
        int index = getResumeIndex(uid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Возникла ошибка - запрошенного для удаления резюме нет в базе.");
        }
    }

    private int getResumeIndex(String uid) {
        int index = -1;
        for(int i = 0; i < size; i++) {
            if (storage[i].getUuid().toString().equals(uid)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, storage.length, null);
        size = 0;
    }

    public Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }
}
