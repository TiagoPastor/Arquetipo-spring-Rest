package br.com.back.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInput {

	@ApiModelProperty(example = "Gerente", required = true)
    @NotBlank
	private String name;
	
}
