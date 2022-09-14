package org.example.converter.provider;

import lombok.Builder;
import net.bytebuddy.description.annotation.AnnotationDescription;
import org.example.api.catalog.Annot2;
import org.example.converter.api.IFieldAnnotationProvider;
import org.jetbrains.annotations.NotNull;

@Builder
public class Annot2Provider implements IFieldAnnotationProvider {
    @NotNull
    private final String param1;

    @Override
    public AnnotationDescription get() {
        return AnnotationDescription.Builder.ofType(Annot2.class).define("param1", param1).build();
    }
}
