package br.com.back.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionModel extends RepresentationModel<PermissionModel>{

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "CONSULTAR_USUARIOS")
	private String name;

	@ApiModelProperty(example = "Permite consultar usuários")
	private String description;
	
}
