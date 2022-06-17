package ru.itis.gitstats.utils.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithName;

import java.io.InputStream;
import java.lang.reflect.Modifier;

public class JavaFileParser extends Parser {

    public JavaFileParser(PackageFilter filter) {
        super(filter);
    }

    @Override
    public JavaClass parse(InputStream is) {
        JavaClass aClass = new JavaClass("");

        JavaParser parser = new JavaParser();
        ParseResult<CompilationUnit> result = parser.parse(is);
        CompilationUnit unit = result.getResult()
                .orElseThrow(() -> new IllegalArgumentException("Parse error"));

        for (ImportDeclaration importDeclaration : unit.getImports()) {
            if (importDeclaration.getNameAsString() != null) {
                aClass.addImportedPackage(new JavaPackage(importDeclaration.getNameAsString()));
            }
        }
        aClass.setClassName(unit.getClass().getName());
        aClass.setAbstract(Modifier.isAbstract(unit.getClass().getModifiers()));
        aClass.setPackageName(unit.getPackageDeclaration().map(NodeWithName::getNameAsString).orElse(""));
        return aClass;
    }
}
