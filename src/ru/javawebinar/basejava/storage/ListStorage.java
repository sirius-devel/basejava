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
        storage.set(((Integer) searchKey).intValue(), resume);
    }

    @Override
    protected void saveElement(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storage.get(((Integer) searchKey).intValue());
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> getElementsAsList() {
        return new ArrayList<Resume>(storage);
    }
}
