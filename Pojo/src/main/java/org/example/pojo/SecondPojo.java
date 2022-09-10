package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.pojo.Pojo;
import org.jetbrains.annotations.NotNull;

@Pojo
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondPojo{
	@NotNull
	private String firstField;
	@NotNull
	private String secondField;
	private String thirdField;
	private String fourthField;
}
