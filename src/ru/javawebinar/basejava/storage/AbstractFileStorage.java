package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void updateElement(Resume resume, File file){

    }

    @Override
    protected void saveElement(Resume resume, File file){
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteElement(File file) {

    }

    @Override
    protected Resume getElement(File file) {
        return null;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return null;
    }

    protected boolean isExist(File file) {
        return file.exists();
    }

    protected List<Resume> getElementsAsList() {
        return null;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;
}