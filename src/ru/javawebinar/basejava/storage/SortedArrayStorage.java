package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    public void update(Resume r) {
        int index = getIndex(r.getUuid().toString());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Ошибка -  вы пытаетесь обновить резюме, которого нет в базе.");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid().toString());
        if (index < 0) {
            size++;
            if (size <= storage.length) {
                int insertionPoint = -index - 1;
                System.arraycopy(storage, insertionPoint, storage, insertionPoint+1, size - 1 - insertionPoint);
                storage[insertionPoint] = r;
            } else {
                System.out.println("Ошибка - к сожалению, хранилище резюме полностью заполнено.");
            }
        } else {
            System.out.println("Ошибка -  вы пытаетесь сохранить резюме, которое уже есть в базе. Вызвана процедура обновления.");
            update(r);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Возникла ошибка - запрошенного для удаления резюме нет в базе.");
        }
    }

    protected int getIndex(String uid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
