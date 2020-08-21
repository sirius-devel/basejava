package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected void updateElement(Resume resume, Integer searchKey) {
        storage.set(searchKey.intValue(), resume);
    }

    @Override
    protected void saveElement(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    protected Resume getElement(Integer searchKey) {
        return storage.get(searchKey.intValue());
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
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> getElementsAsList() {
        return new ArrayList<Resume>(storage);
    }
}
