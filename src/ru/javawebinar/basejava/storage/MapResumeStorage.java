package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    protected Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveElement(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteElement(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected Resume getElement(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> getElementsAsList() {
        return new ArrayList<>(storage.values());
    }
}
