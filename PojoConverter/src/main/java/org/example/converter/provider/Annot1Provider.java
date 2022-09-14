package org.example.converter.provider;

import lombok.Builder;
import net.bytebuddy.description.annotation.AnnotationDescription;
import org.example.api.catalog.Annot1;
import org.example.converter.api.IFieldAnnotationProvider;

@Builder
public class Annot1Provider implements IFieldAnnotationProvider {
    @Override
    public AnnotationDescription get() {
        return AnnotationDescription.Builder.ofType(Annot1.class).build();
    }
}
