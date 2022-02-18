package br.com.back.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "users")
@Getter
@Setter
public class UsersModel extends RepresentationModel<UsersModel>{

	@ApiModelProperty(example = "1")
    private Long id;	
	
	@ApiModelProperty(example = "joaodasilva@email.com")
	private String userEmail;
	
	@ApiModelProperty(example = "1")
	private String status;
	
	@ApiModelProperty(example = "João")
	private String userName;
	
	@ApiModelProperty(example = "image.jpg")
	private String photo;

}
