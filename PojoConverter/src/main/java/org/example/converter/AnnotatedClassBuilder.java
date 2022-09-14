package org.example.converter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.matcher.NameMatcher;
import org.example.api.catalog.CatalogEntry;
import org.example.converter.api.IAnnotationProvider;
import org.example.converter.api.IConfigurationSupplier;
import org.example.converter.definition.ClassDefinition;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;
import java.util.Objects;

@AllArgsConstructor
@Builder
public class AnnotatedClassBuilder {
    public void convert(@NotNull IConfigurationSupplier configurationProvider) {
        try {
            var configuration = configurationProvider.get();
            configuration.getClasses().forEach(this::annotateClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @SneakyThrows
    private void annotateClass(@NotNull Class<?> klass, @NotNull ClassDefinition definition) {
        var byteBuddy = new ByteBuddy().redefine(klass);
        byteBuddy = byteBuddy.implement(CatalogEntry.class);

        byteBuddy = byteBuddy.annotateType(definition.getAnnotations().stream().map(IAnnotationProvider::get).toList());

        for (var field : klass.getDeclaredFields()) {
            var annotationProvider = definition.getFields().get(field.getName());
            if (Objects.nonNull(annotationProvider)) {
                byteBuddy = byteBuddy.field(new NameMatcher<>(name -> Objects.equals(name, field.getName())))
                        .annotateField(annotationProvider.stream().map(IAnnotationProvider::get).toList());
            }
        }

        try (var unloaded = byteBuddy.make()) {
            var classFile = Paths.get("build/classes/java/main");
            unloaded.saveIn(classFile.toFile());
        }
    }
}
