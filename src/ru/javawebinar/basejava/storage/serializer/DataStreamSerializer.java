package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataStreamSerializer implements StreamSerializer{

    public DataStreamSerializer() {
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        return null;
    }
}
