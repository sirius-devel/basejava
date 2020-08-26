package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {

    abstract void doWrite(Resume r, OutputStream os) throws IOException;

    abstract Resume doRead(InputStream is) throws IOException;
}
