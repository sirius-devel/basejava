package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        updateElement(resume, existSearchKey(resume.getUuid()));
    }

    public void save(Resume resume) {
        saveElement(resume, newSearchKey(resume.getUuid()));
    }

    public Resume get(String uuid) {
        return getElement(existSearchKey(uuid));
    }

    public void delete(String uuid) {
        deleteElement(existSearchKey(uuid));
    }

    private Object existSearchKey(String uuid) {
        if (!containElement(getSearchKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    private Object newSearchKey(String uuid) {
        if (containElement(getSearchKey(uuid))) {
            throw new ExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    protected abstract void updateElement(Resume resume, Object searchKey);

    protected abstract void saveElement(Resume resume, Object searchKey);

    protected abstract void deleteElement(Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean containElement(Object searchKey);
}
