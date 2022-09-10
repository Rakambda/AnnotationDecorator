package org.example.converter.util;

import com.fasterxml.jackson.annotation.JsonSetter.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Objects;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.Nulls.AS_EMPTY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.CREATOR;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.annotation.PropertyAccessor.GETTER;
import static com.fasterxml.jackson.annotation.PropertyAccessor.SETTER;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS;
import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class JacksonUtils{
	private static ObjectMapper jsonMapper;
	private static ObjectMapper yamlMapper;
	
	@NotNull
	public static ObjectMapper getYamlMapper(){
		if(Objects.isNull(yamlMapper)){
			yamlMapper = YAMLMapper.builder()
					.enable(SORT_PROPERTIES_ALPHABETICALLY)
					.enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
					.enable(ALLOW_COMMENTS)
					.visibility(FIELD, ANY)
					.visibility(GETTER, NONE)
					.visibility(SETTER, NONE)
					.visibility(CREATOR, NONE)
					.serializationInclusion(NON_NULL)
					.withConfigOverride(List.class, o -> o.setSetterInfo(Value.forValueNulls(AS_EMPTY)))
					.findAndAddModules()
					.build();
		}
		return yamlMapper;
	}
	
	@NotNull
	public static ObjectMapper getJsonMapper(){
		if(Objects.isNull(jsonMapper)){
			jsonMapper = JsonMapper.builder()
					.enable(SORT_PROPERTIES_ALPHABETICALLY)
					.enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
					.enable(ALLOW_COMMENTS)
					.visibility(FIELD, ANY)
					.visibility(GETTER, NONE)
					.visibility(SETTER, NONE)
					.visibility(CREATOR, NONE)
					.serializationInclusion(NON_NULL)
					.withConfigOverride(List.class, o -> o.setSetterInfo(Value.forValueNulls(AS_EMPTY)))
					.findAndAddModules()
					.build();
		}
		return jsonMapper;
	}
}
