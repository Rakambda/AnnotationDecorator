package org.example.converter.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Definition{
	@NotNull
	private String packageName;
	@NotNull
	private Map<String, ClassDefinition> classes = new HashMap<>();
}
