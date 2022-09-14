package org.example.converter.definition;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Definition {
    @NotNull
    @Singular(value = "klass")
    private Map<Class<?>, ClassDefinition> classes = new HashMap<>();
}
