package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printDirectoryDeeply(dir, "");
    }

    public static void printDirectoryDeeply(File dir, String space) {
        String[] list = dir.list();
        space = new StringBuilder().append(space).append("    ").toString();
        int i;
        if (list != null) {
            for (i = 0; i < list.length; i++) {
                File file = new File(dir.getPath() +
                        File.separator + list[i]);
                if (file.isFile())
                    System.out.println(space + "File: " + file.getName());
                else if (file.isDirectory()) {
                    System.out.println(space +"Directory: " + file.getPath());
                    printDirectoryDeeply(file, space);
                }
            }
        }
    }
}

