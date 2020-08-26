package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String > {
    private final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveElement(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void updateElement(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void deleteElement(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getElement(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected List<Resume> getElementsAsList() {
        return new ArrayList<>(storage.values());
    }
}
