package ru.itis.gitstats.utils.parser;

import lombok.Data;

import java.io.*;
import java.util.*;

@Data
public abstract class Parser {

    private ArrayList<ParserListener> parseListeners;
    private PackageFilter filter;

    public Parser(PackageFilter filter) {
        setFilter(filter);
        parseListeners = new ArrayList<>();
    }

    public abstract JavaClass parse(InputStream is) throws IOException;

    protected void onParsedJavaClass(JavaClass jClass) {
        for (ParserListener parseListener : parseListeners) {
            parseListener.onParsedJavaClass(jClass);
        }
    }

    protected PackageFilter getFilter() {
        if (filter == null) {
            setFilter(new PackageFilter());
        }
        return filter;
    }
}