package ru.itis.gitstats.utils.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaClass {

    private String className;
    private String packageName;
    private boolean isAbstract;
    private HashMap<String, JavaPackage> imports;
    private String sourceFile;

    public JavaClass(String name) {
        className = name;
        packageName = "default";
        isAbstract = false;
        imports = new HashMap<>();
        sourceFile = "Unknown";
    }

    public Collection<JavaPackage> getImportedPackages() {
        return imports.values();
    }

    public void addImportedPackage(JavaPackage jPackage) {
        if (!jPackage.getName().equals(getPackageName())) {
            imports.put(jPackage.getName(), jPackage);
        }
    }
}
