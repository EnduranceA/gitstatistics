package ru.itis.gitstats.utils.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaPackage {

    private String name;
    private int volatility;
    private HashSet<JavaClass> classes;
    private List<JavaPackage> afferentPackages;
    private List<JavaPackage> efferentPackages;

    public JavaPackage(String name) {
        this(name, 1);
    }

    public JavaPackage(String name, int volatility) {
        String str2 = name.toLowerCase();
        if (!str2.equals(name)) {
            String[] parts = name.split("\\.");
            String lastWord = parts[parts.length - 1];
            name = name.substring(0, name.length() - lastWord.length() - 1);
        }
        this.name = name;
        this.volatility = volatility;
        this.classes = new HashSet<>();
        this.afferentPackages = new ArrayList<>();
        this.efferentPackages = new ArrayList<>();
    }

    public void addClass(JavaClass clazz) {
        classes.add(clazz);
    }

    public int getClassCount() {
        return classes.size();
    }

    public int getAbstractClassCount() {
        int count = 0;
        for (JavaClass clazz : classes) {
            if (clazz.isAbstract()) {
                count++;
            }
        }
        return count;
    }

    public void dependsUpon(JavaPackage imported) {
        addEfferent(imported);
        imported.addAfferent(this);
    }

    //Добавление пакета в список, с котором есть афферентная связь
    public void addAfferent(JavaPackage jPackage) {
        if (!jPackage.getName().equals(getName())) {
            if (!afferentPackages.contains(jPackage)) {
                afferentPackages.add(jPackage);
            }
        }
    }

    //Добавление пакета в список, с котором есть эфферентная связь
    public void addEfferent(JavaPackage jPackage) {
        if (!jPackage.getName().equals(getName())) {
            if (!efferentPackages.contains(jPackage)) {
                efferentPackages.add(jPackage);
            }
        }
    }

    //Получение значения афферентности данного пакета
    public int afferentCoupling() {
        return afferentPackages.size();
    }

    //Получение значения эфферентности данного пакета
    public int efferentCoupling() {
        return efferentPackages.size();
    }

    //Нахождение значения стабильности
    public float instability() {
        float totalCoupling = (float) efferentCoupling()
                + (float) afferentCoupling();
        if (totalCoupling > 0) {
            return efferentCoupling()/totalCoupling;
        }
        return 0;
    }

    //Нахождение абстрактности пакета
    public float abstractness() {
        if (getClassCount() > 0) {
            return (float) getAbstractClassCount() / (float) getClassCount();
        }
        return 0;
    }

    //Нахождение расстояние до главной последовательности
    public float distance() {
        float d = Math.abs(abstractness() + instability() - 1);
        return d * volatility;
    }

}
