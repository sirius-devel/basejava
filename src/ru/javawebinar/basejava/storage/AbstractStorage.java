package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume resume) {
        LOG.info("update " + resume);
        updateElement(resume, getExistedSearchKey(resume.getUuid()));
    }

    public void save(Resume resume) {
        LOG.info("save " + resume);
        saveElement(resume, getNotExistedSearchKey(resume.getUuid()));
    }

    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        return getElement(getExistedSearchKey(uuid));
    }

    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        deleteElement(getExistedSearchKey(uuid));
    }

    private SK getExistedSearchKey(String uuid) {
        if (!isExist(getSearchKey(uuid))) {
            LOG.warning("resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    private SK getNotExistedSearchKey(String uuid) {
        if (isExist(getSearchKey(uuid))) {
            LOG.warning("resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    public List<Resume> getAllSorted() {
        LOG.info("get all sorted");
        List<Resume> list = getElementsAsList();
        Collections.sort(list);
        return list;
    }

    protected abstract void updateElement(Resume resume, SK searchKey);

    protected abstract void saveElement(Resume resume, SK searchKey);

    protected abstract void deleteElement(SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getElementsAsList();
}
