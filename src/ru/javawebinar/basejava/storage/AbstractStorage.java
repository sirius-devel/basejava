package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        updateElement(resume, getExistedSearchKey(resume.getUuid()));
    }

    public void save(Resume resume) {
        saveElement(resume, getNotExistedSearchKey(resume.getUuid()));
    }

    public Resume get(String uuid) {
        return getElement(getExistedSearchKey(uuid));
    }

    public void delete(String uuid) {
        deleteElement(getExistedSearchKey(uuid));
    }

    private Object getExistedSearchKey(String uuid) {
        if (!containElement(getSearchKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    private Object getNotExistedSearchKey(String uuid) {
        if (containElement(getSearchKey(uuid))) {
            throw new ExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getElementsAsList();
        Collections.sort(list);
        return list;
    }

    protected abstract void updateElement(Resume resume, Object searchKey);

    protected abstract void saveElement(Resume resume, Object searchKey);

    protected abstract void deleteElement(Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean containElement(Object searchKey);

    protected abstract List<Resume> getElementsAsList();
}
