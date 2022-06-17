package ru.itis.gitstats.utils.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;


public class PropertyConfigurator {

    private final Properties properties;

    public static final String DEFAULT_PROPERTY_FILE = "jdepend.properties";

    public PropertyConfigurator() {
        this(getDefaultPropertyFile());
    }

    public PropertyConfigurator(Properties p) {
        this.properties = p;
    }

    public PropertyConfigurator(File f) {
        this(loadProperties(f));
    }

    public Collection<String> getFilteredPackages() {
        Collection<String> packages = new ArrayList<>();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (key.startsWith("ignore")) {
                String path = properties.getProperty(key);
                StringTokenizer st = new StringTokenizer(path, ",");
                while (st.hasMoreTokens()) {
                    String name = st.nextToken();
                    name = name.trim();
                    packages.add(name);
                }
            }
        }

        return packages;
    }

    public Collection<JavaPackage> getConfiguredPackages() {
        Collection<JavaPackage> packages = new ArrayList<>();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String)e.nextElement();
            if (!key.startsWith("ignore")
                    && (!key.equals("analyzeInnerClasses"))) {
                String v = properties.getProperty(key);
                packages.add(new JavaPackage(key, Integer.parseInt(v)));
            }
        }
        return packages;
    }

    public boolean getAnalyzeInnerClasses() {
        String key = "analyzeInnerClasses";
        if (properties.containsKey(key)) {
            String value = properties.getProperty(key);
            return Boolean.parseBoolean(value);
        }
        return true;
    }

    public static File getDefaultPropertyFile() {
        String home = System.getProperty("user.home");
        return new File(home, DEFAULT_PROPERTY_FILE);
    }

    public static Properties loadProperties(File file) {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (Exception e) {
            is = PropertyConfigurator.class.getResourceAsStream("/"
                    + DEFAULT_PROPERTY_FILE);
        }
        try {
            if (is != null) {
                p.load(is);
            }
        } catch (IOException ignore) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ignore) {
            }
        }

        return p;
    }
}