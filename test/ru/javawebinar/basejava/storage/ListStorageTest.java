package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import ru.javawebinar.basejava.model.Resume;

public class ListStorageTest extends AbstractStorageTest{
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    public void getAll() throws Exception {
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] array = storage.getAll();
        Assert.assertEquals(3, array.length);
        Assert.assertArrayEquals(resumes, array);
    }
}
