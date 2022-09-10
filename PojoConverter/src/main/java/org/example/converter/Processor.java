package org.example.converter;

import java.io.IOException;
import java.nio.file.Paths;

public class Processor{
	private static final AnnotatedClassBuilder ANNOTATED_CLASS_BUILDER = new AnnotatedClassBuilder();
	
	public static void main(String[] args) throws IOException{
		ANNOTATED_CLASS_BUILDER.convert(Paths.get("src/main/resources/configuration.yml"));
	}
}
