package org.example.converter.definition;

import lombok.*;
import org.example.converter.api.IClassAnnotationProvider;
import org.example.converter.api.IFieldAnnotationProvider;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClassDefinition {
    @NotNull
    @Singular
    private Collection<IClassAnnotationProvider> annotations = new LinkedList<>();
    @NotNull
    @Singular
    private Map<String, Collection<IFieldAnnotationProvider>> fields = new HashMap<>();
}
