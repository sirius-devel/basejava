package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[size - 1] = resume;
    }

    protected int getIndex(String uuid) {
        for(int i = 0; i < size; i++) {
            Resume resume = new Resume(uuid);
            if (storage[i].equals(resume)) {
                return i;
            }
        }
        return -1;
    }
}
