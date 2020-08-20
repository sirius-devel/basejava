package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorageTest extends AbstractStorageTest{
    public MapStorageTest() {
        super(new MapUuidStorage());
    }

    @Override
    public void getAll() throws Exception {
        Map<String, Resume> resumes = new HashMap<>();
        resumes.put(UUID_1, RESUME_1);
        resumes.put(UUID_2, RESUME_2);
        resumes.put(UUID_3, RESUME_3);
        Resume[] array = storage.getAll();
        Map<String, Resume> getArray = new HashMap<>();
        for(Resume r : array) {
            getArray.put(r.getUuid(), r);
        }
        Assert.assertEquals(3, array.length);
        Assert.assertEquals(true, getArray.equals(resumes));
    }
}
