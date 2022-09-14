package org.example.converter.api;

import net.bytebuddy.description.annotation.AnnotationDescription;

import java.util.function.Supplier;

public interface IAnnotationProvider extends Supplier<AnnotationDescription> {
}
