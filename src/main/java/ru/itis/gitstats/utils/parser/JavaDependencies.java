package ru.itis.gitstats.utils.parser;

import java.io.IOException;
import java.util.*;

public class JavaDependencies {

    private final HashMap<String, JavaPackage> packages;
    private final FileManager fileManager;
    private PackageFilter filter;
    private final Parser parser;
    private final JavaClassBuilder builder;
    private Collection<String> components;

    public JavaDependencies() {
        this(new PackageFilter());
    }

    public JavaDependencies(PackageFilter filter) {
        setFilter(filter);
        this.packages = new HashMap<>();
        this.fileManager = new FileManager();
        this.parser = new JavaFileParser(filter);
        this.builder = new JavaClassBuilder(parser, fileManager);
        PropertyConfigurator config = new PropertyConfigurator();
        addPackages(config.getConfiguredPackages());
        analyzeInnerClasses(config.getAnalyzeInnerClasses());
    }

    public Collection<JavaPackage> analyze() {
        Collection<JavaClass> classes = builder.build();
        for (JavaClass aClass : classes) {
            analyzeClass(aClass);
        }
        return getPackages();
    }

    public void addDirectory(String name) {
        try {
            fileManager.addDirectory(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setComponents(String components) {
        this.components = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(components, ",");
        while (st.hasMoreTokens()) {
            String component = st.nextToken();
            this.components.add(component);
        }
    }

    public void analyzeInnerClasses(boolean b) {
        fileManager.acceptInnerClasses(b);
    }

    public Collection<JavaPackage> getPackages() {
        return packages.values();
    }

    public JavaPackage addPackage(String name) {
        name = toComponent(name);
        JavaPackage pkg = packages.get(name);
        if (pkg == null) {
            pkg = new JavaPackage(name);
            addPackage(pkg);
        }

        return pkg;
    }

    private String toComponent(String packageName) {
        if (components != null) {
            for (String component : components) {
                if (packageName.startsWith(component + ".")) {
                    return component;
                }
            }
        }
        return packageName;
    }

    public void addPackages(Collection<JavaPackage> packages) {
        for (JavaPackage pkg : packages) {
            addPackage(pkg);
        }
    }

    public void addPackage(JavaPackage pkg) {
        if (!packages.containsValue(pkg)) {
            String name = pkg.getName();
            String str2 = name.toLowerCase();
            if (!str2.equals(name)) {
                String[] parts = name.split("\\.");
                String lastWord = parts[parts.length - 1];
                name = name.substring(0, name.length() - lastWord.length());
                packages.put(name, pkg);
            }
            else packages.put(pkg.getName(), pkg);
        }
    }

    public PackageFilter getFilter() {
        if (filter == null) {
            filter = new PackageFilter();
        }
        return filter;
    }

    public void setFilter(PackageFilter filter) {
        if (parser != null) {
            parser.setFilter(filter);
        }
        this.filter = filter;
    }

    private void analyzeClass(JavaClass clazz) {
        String packageName = clazz.getPackageName();
        if (!getFilter().accept(packageName)) {
            return;
        }
        JavaPackage clazzPackage = addPackage(packageName);
        clazzPackage.addClass(clazz);

        Collection<JavaPackage> imports = clazz.getImportedPackages();
        for (JavaPackage anImport : imports) {
            anImport = addPackage(anImport.getName());
            clazzPackage.dependsUpon(anImport);
        }
    }
}
