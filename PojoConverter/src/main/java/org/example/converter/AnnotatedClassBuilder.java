package org.example.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.matcher.NameMatcher;
import org.example.api.catalog.Annot1;
import org.example.api.catalog.Annot2;
import org.example.api.catalog.CatalogEntry;
import org.example.api.pojo.Pojo;
import org.example.converter.definition.ClassDefinition;
import org.example.converter.definition.Definition;
import org.example.converter.definition.FieldDefinition;
import org.example.converter.util.JacksonUtils;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@AllArgsConstructor
@Builder
public class AnnotatedClassBuilder{
	public void convert(@NotNull Path definitionPath) throws IOException{
		try{
            var definition = loadDefinition(definitionPath);

            new Reflections(definition.getPackageName())
                    .get(SubTypes.of(TypesAnnotated.with(Pojo.class)).asClass())
                    .forEach(klass -> {
                        var def = Optional.ofNullable(definition.getClasses().get(klass.getSimpleName()))
                                .orElseThrow(() -> new RuntimeException("Class " + klass + " has no definition"));

                        annotateClass(klass, def);
                    });
		}
		catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @NotNull
	private Definition loadDefinition(@NotNull Path definitionPath) throws IOException{
		try(var is = Files.newInputStream(definitionPath)){
			return JacksonUtils.getYamlMapper().readValue(is, new TypeReference<>(){});
        }
    }

    @SneakyThrows
	private void annotateClass(@NotNull Class<?> klass, @NotNull ClassDefinition definition){
		var byteBuddy = new ByteBuddy()
                .redefine(klass);
        byteBuddy = byteBuddy.implement(CatalogEntry.class);
		for(var field : klass.getDeclaredFields()){
            var def = definition.getFields().get(field.getName());
			if(Objects.nonNull(def)){
                byteBuddy = byteBuddy.field(new NameMatcher<>(name -> Objects.equals(name, field.getName()))).annotateField(buildAnnotations(def));
            }
        }
		try(var unloaded = byteBuddy.make()){
            var classFile = Paths.get("build/classes/java/main");
            unloaded.saveIn(classFile.toFile());
        }
    }

    @NotNull
	private Collection<? extends AnnotationDescription> buildAnnotations(@NotNull FieldDefinition definition){
        var annotations = new LinkedList<AnnotationDescription>();

		if(definition.isAnnot1()){
            annotations.add(AnnotationDescription.Builder.ofType(Annot1.class).build());
        }
		if(definition.isAnnot2()){
            annotations.add(AnnotationDescription.Builder.ofType(Annot2.class).define("param1", "value1").build());
        }

        return annotations;
    }
}
