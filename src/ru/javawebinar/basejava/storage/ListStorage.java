package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected void updateElement(Resume resume, Object searchKey) {
        storage.set((int) searchKey, resume);
    }

    @Override
    protected void saveElement(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean containElement(Object searchKey) {
        return (((Integer) searchKey) >= 0) && (((int) searchKey) < storage.size());
    }

    @Override
    protected List<Resume> getElementsAsList() {
        return new ArrayList<Resume>(storage);
    }
}
