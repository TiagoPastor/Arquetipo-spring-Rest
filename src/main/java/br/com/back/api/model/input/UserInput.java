package br.com.back.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

	@ApiModelProperty(example = "João da Silva", required = true)
	@NotBlank
	private String userName;
	
	@ApiModelProperty(example = "joao.ger@corporative.com.br", required = true)
	@NotBlank
	@Email
	private String userEmail;
	
}
