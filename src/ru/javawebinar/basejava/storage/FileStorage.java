package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private final Strategy strategy;

    protected FileStorage(File directory, Strategy strategy) {
        this.strategy = strategy;
        Objects.requireNonNull(directory, "directory must be not null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override //clear only files in directory
    public void clear() {
        File[] files = directory.listFiles();
        if(files != null) {
            for(File file : files) {
                deleteElement(file);
            }
        }
    }

    @Override //directory contains only files
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("read directory error", null);
        }
        return list.length;
    }

    @Override
    protected void updateElement(Resume resume, File file) {
        try {
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("write to file error", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveElement(Resume resume, File file){
        try {
            file.createNewFile();
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
    }

    @Override
    protected void deleteElement(File file) {
        if (!file.delete()) {
            throw new StorageException("delete file error", file.getName());
        }
    }

    @Override
    protected Resume getElement(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("read file error", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getElementsAsList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("read directory error", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(getElement(file));
        }
        return list;
    }
}