package ru.itis.gitstats.utils.parser;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;

@Data
@AllArgsConstructor
public class JavaClassBuilder {

    private Parser parser;
    private FileManager fileManager;

    public Collection<JavaClass> build() {
        Collection<JavaClass> classes = new ArrayList<>();
        for (File file : fileManager.extractFiles()) {
            try {
                classes.addAll(buildClasses(file));
            } catch (IOException ioe) {
                System.err.println("\n" + ioe.getMessage());
            }
        }
        return classes;
    }

    public Collection<JavaClass> buildClasses(File file) throws IOException {
        if (fileManager.acceptJavaFile(file)) {
            try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
                JavaClass parsedClass = parser.parse(is);
                Collection<JavaClass> javaClasses = new ArrayList<>();
                javaClasses.add(parsedClass);
                return javaClasses;
            }
        } else if (fileManager.acceptJarFile(file)) {
            JarFile jarFile = new JarFile(file);
            Collection<JavaClass> result = buildClasses(jarFile);
            jarFile.close();
            return result;
        } else {
            throw new IOException("File is not a valid " + 
                ".class, .jar, .war, or .zip file: " + 
                file.getPath());
        }
    }

    public Collection<JavaClass> buildClasses(JarFile file) throws IOException {
        Collection<JavaClass> javaClasses = new ArrayList<>();
        Enumeration<JarEntry> entries = file.entries();
        while (entries.hasMoreElements()) {
            ZipEntry e = entries.nextElement();
            if (fileManager.acceptJavaFileName(e.getName())) {
                try (InputStream is = new BufferedInputStream(file.getInputStream(e))) {
                    JavaClass jc = parser.parse(is);
                    javaClasses.add(jc);
                }
            }
        }
        return javaClasses;
    }
}
