package lesson_1;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage;
    private int resumeCount;

    ArrayStorage() {
        storage = new Resume[10000];
        resumeCount = 0;
    }

    public void save(Resume r) {
        resumeCount++;
        if (resumeCount <= storage.length) {
            storage[resumeCount - 1] = r;
        }
    }

    public Resume get(String uid) {
        Resume result = null;
        for(int i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().toString().equals(uid)) {
                result = storage[i];
                break;
            }
        }
        return result;
    }

    public void delete(String uid) {
        int i;
        for(i = 0; i < resumeCount; i++) {
            if (storage[i].getUuid().toString().equals(uid)) {
                break;
            }
        }
        System.arraycopy(storage, i + 1, storage, i, resumeCount - 1 - i);
        storage[resumeCount-1] = null;
        resumeCount--;
    }

    public int size() {
        return resumeCount;
    }

    public void clear() {
        Arrays.fill(storage, 0, storage.length, null);
        resumeCount = 0;
    }

    Resume[] getAll() {
        return storage;
    }
}
