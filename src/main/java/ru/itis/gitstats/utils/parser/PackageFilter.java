package ru.itis.gitstats.utils.parser;

import java.io.*;
import java.util.*;

public class PackageFilter {

    private final Set<String> filtered;

    public PackageFilter() {
        this(new HashSet<>());
        PropertyConfigurator config = new PropertyConfigurator();
        addPackages(config.getFilteredPackages());
    }

    public PackageFilter(File f) {
        this(new HashSet<>());
        PropertyConfigurator config = new PropertyConfigurator(f);
        addPackages(config.getFilteredPackages());
    }

    public PackageFilter(Collection<String> packageNames) {
        filtered = new HashSet<>();
        addPackages(packageNames);
    }

    public Set<String> getFiltered() {
        return filtered;
    }

    public boolean accept(String packageName) {
        for (String o : getFiltered()) {
            if (packageName.startsWith(o)) {
                return false;
            }
        }
        return true;
    }

    public void addPackages(Collection<String> packageNames) {
        for (String packageName : packageNames) {
            addPackage(packageName);
        }
    }

    public void addPackage(String packageName) {
        if (packageName.endsWith("*")) {
            packageName = packageName.substring(0, packageName.length() - 1);
        }
        else {
            String str2 = packageName.toLowerCase();
            if (!str2.equals(packageName)) {
                String[] parts = packageName.split("\\.");
                String lastWord = parts[parts.length - 1];
                packageName = packageName.substring(0, packageName.length() - lastWord.length());
            }
        }
        if (packageName.length() > 0) {
            getFiltered().add(packageName);
        }
    }
}
