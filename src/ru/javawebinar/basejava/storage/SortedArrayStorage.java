package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
    */

    /*private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };*/

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void insertElement(Resume resume, Integer index) {
        int insertionPoint = -index - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint+1, size - 1 - insertionPoint);
        storage[insertionPoint] = resume;
    }

    @Override
    protected void removeElement(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, resume, RESUME_COMPARATOR);
    }
}
