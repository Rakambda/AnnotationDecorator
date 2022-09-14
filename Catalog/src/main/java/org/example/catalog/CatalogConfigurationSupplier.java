package org.example.catalog;

import org.example.converter.api.IConfigurationSupplier;
import org.example.converter.definition.ClassDefinition;
import org.example.converter.definition.Definition;
import org.example.converter.provider.Annot1Provider;
import org.example.converter.provider.Annot2Provider;
import org.example.converter.provider.klass.KAnnot1Provider;
import org.example.pojo.FirstPojo;
import org.example.pojo.SecondPojo;

import java.util.List;

public class CatalogConfigurationSupplier implements IConfigurationSupplier {
    @Override
    public Definition get() {
        return Definition.builder()
                .klass(FirstPojo.class, ClassDefinition.builder()
                        .annotation(KAnnot1Provider.builder().build())
                        .field("firstField", List.of(Annot1Provider.builder().build()))
                        .field("thirdField", List.of(Annot2Provider.builder().param1("value1").build()))
                        .field("fourthField", List.of(Annot1Provider.builder().build(), Annot2Provider.builder().param1("value1").build()))
                        .build())
                .klass(SecondPojo.class, ClassDefinition.builder().build())
                .build();
    }
}
