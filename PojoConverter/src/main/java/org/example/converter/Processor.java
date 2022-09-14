package org.example.converter;

import org.example.converter.api.IConfigurationSupplier;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class Processor {
    private static final AnnotatedClassBuilder ANNOTATED_CLASS_BUILDER = new AnnotatedClassBuilder();

    public static void main(String[] args) {
        var reflections = new Reflections("org.example"); //TODO Improve this. Scan all classpaths ?

        reflections.getSubTypesOf(IConfigurationSupplier.class)
                .stream()
                .map(klass -> {
                    try {
                        return klass.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(ANNOTATED_CLASS_BUILDER::convert);
    }
}
