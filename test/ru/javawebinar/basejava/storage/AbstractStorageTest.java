package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;
import static ru.javawebinar.basejava.ResumeTestData.*;
import java.io.File;


public abstract class AbstractStorageTest {
    protected Storage storage;
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected  AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        storage.update(R3);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void save() throws Exception {
        int sizeBefore = storage.size();
        storage.save(R4);
        assertSize(++sizeBefore);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        int sizeBefore = storage.size();
        storage.delete(UUID_3);
        assertSize(--sizeBefore);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expectedResumes = Arrays.asList(R1, R2, R3);
        Collections.sort(expectedResumes);
        List<Resume> gettingResumes = storage.getAllSorted();
        Assert.assertEquals(3, gettingResumes.size());
        Assert.assertEquals(expectedResumes, gettingResumes);
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}