package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
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

    public int size() {
        return size;
    }

    @Override
    protected void updateElement(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected void saveElement(Resume resume, Object searchKey) {
        if (size + 1 <= storage.length) {
            size++;
            insertElement(resume, (Integer) searchKey);
        } else {
            throw new StorageException("К сожалению, хранилище резюме полностью заполнено.", resume.getUuid());
        }
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void deleteElement(Object searchKey) {
        removeElement((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract void insertElement(Resume resume, Integer index);

    protected abstract void removeElement(Integer index);

    @Override
    protected List<Resume> getElementsAsList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}