package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertionPoint = -index - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint+1, size - 1 - insertionPoint);
        storage[insertionPoint] = resume;
    }

    protected int getIndex(String uid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
