package org.example.converter.provider.klass;

import lombok.Builder;
import net.bytebuddy.description.annotation.AnnotationDescription;
import org.example.api.catalog.KAnnot1;
import org.example.converter.api.IClassAnnotationProvider;

@Builder
public class KAnnot1Provider implements IClassAnnotationProvider {
    @Override
    public AnnotationDescription get() {
        return AnnotationDescription.Builder.ofType(KAnnot1.class).build();
    }
}
