package ru.itis.gitstats.utils.parser;

import java.io.*;
import java.util.*;


public class FileManager {

    private final Set<File> directories;
    private boolean acceptInnerClasses;

    public FileManager() {
        this.directories = new HashSet<>();
        this.acceptInnerClasses = true;
    }

    public void acceptInnerClasses(boolean b) {
        acceptInnerClasses = b;
    }

    public void addDirectory(String name) throws IOException {
        File directory = new File(name);
        if (directory.isDirectory() || acceptJarFile(directory)) {
            directories.add(directory);
        } else {
            throw new IOException("Invalid directory or JAR file: " + name);
        }
    }

    public boolean acceptFile(File file) {
        return acceptJavaFile(file) || acceptJarFile(file);
    }

    public boolean acceptJavaFile(File file) {
        if (!file.isFile()) {
            return false;
        }
        return acceptJavaFileName(file.getName());
    }

    public boolean acceptJavaFileName(String name) {
        if (!acceptInnerClasses) {
            if (name.toLowerCase().indexOf("$") > 0) {
                return false;
            }
        }
        return name.toLowerCase().endsWith(".java");
    }

    public boolean acceptJarFile(File file) {
        return isJar(file) || isZip(file) || isWar(file);
    }

    public Collection<File> extractFiles() {
        Collection<File> files = new TreeSet<>();
        for (File directory : directories) {
            collectFiles(directory, files);
        }
        return files;
    }

    private void collectFiles(File directory, Collection<File> files) {
        if (directory.isFile()) {
            addFile(directory, files);
        } else {
            String[] directoryFiles = directory.list();
            if (directoryFiles != null) {
                for (String directoryFile : directoryFiles) {
                    File file = new File(directory, directoryFile);
                    if (acceptFile(file)) {
                        addFile(file, files);
                    } else if (file.isDirectory()) {
                        collectFiles(file, files);
                    }
                }
            }
        }
    }

    private void addFile(File f, Collection<File> files) {
        if (!files.contains(f)) {
            files.add(f);
        }
    }

    private boolean isWar(File file) {
        return existsWithExtension(file, ".war");
    }

    private boolean isZip(File file) {
        return existsWithExtension(file, ".zip");
    }

    private boolean isJar(File file) {
        return existsWithExtension(file, ".jar");
    }

    private boolean existsWithExtension(File file, String extension) {
        return file.isFile() &&
            file.getName().toLowerCase().endsWith(extension);
    }

}