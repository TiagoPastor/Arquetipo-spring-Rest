package br.com.back.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.back.api.model.PermissionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissionControllerOpenApi {
	
	@ApiOperation("Lsita as pemrissões")
	CollectionModel<PermissionModel> list();

}
