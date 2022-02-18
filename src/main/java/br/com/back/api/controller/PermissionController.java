package br.com.back.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.api.assembler.PermissionModelAssembler;
import br.com.back.api.model.PermissionModel;
import br.com.back.api.openapi.controller.PermissionControllerOpenApi;
import br.com.back.core.security.CheckSecurity;
import br.com.back.domain.model.Permission;
import br.com.back.domain.repository.PermissionRepository;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi{

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult	
	@Override
	@GetMapping
	public CollectionModel<PermissionModel> list() {
		List<Permission> allPermissions = permissionRepository.findAll();
		
		return permissionModelAssembler.toCollectionModel(allPermissions);
	}

}
