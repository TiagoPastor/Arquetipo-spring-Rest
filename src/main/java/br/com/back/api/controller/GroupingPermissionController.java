package br.com.back.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.api.BackLinks;
import br.com.back.api.assembler.PermissionModelAssembler;
import br.com.back.api.model.PermissionModel;
import br.com.back.api.openapi.controller.GroupPermissionControllerOpenApi;
import br.com.back.core.security.BackSecurity;
import br.com.back.core.security.CheckSecurity;
import br.com.back.domain.model.Grouping;
import br.com.back.domain.service.GroupingResgistrationService; 

@RestController
@RequestMapping(path = "/groups/{groupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupingPermissionController implements GroupPermissionControllerOpenApi {

	@Autowired
	private GroupingResgistrationService groupingResgistrationService;

	@Autowired
	private PermissionModelAssembler permissionModelAssembler;

	@Autowired
	private BackLinks backLinks;
	
	@Autowired
	private BackSecurity backSecurity;

	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@Override
	@GetMapping
	public CollectionModel<PermissionModel> list(@PathVariable Long groupId) {
		Grouping grouping = groupingResgistrationService.seekOrFail(groupId);
		
		CollectionModel<PermissionModel> permissionsModel
			= permissionModelAssembler.toCollectionModel(grouping.getPermissions())
				.removeLinks();
				
				permissionsModel.add(backLinks.linkToGroupPermissions(groupId));
				
			if(backSecurity.canEditUsersGroupsPermissions()) {
				permissionsModel.add(backLinks.linkToGrupoPermissionAssociate(groupId, "associate"));
				
				permissionsModel.getContent().forEach(permissionModel -> {
					permissionModel.add(backLinks.linkToGrupoPermissaoDesassociate(
							groupId, permissionModel.getId(), "desassociate"));
				});
				
			}	
		
		return permissionsModel;
	}

	@CheckSecurity.UsersGroupsPermissions.CanEdit
	@Override
	@DeleteMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
		groupingResgistrationService.disassociatePermission(groupId, permissionId);
		
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsersGroupsPermissions.CanEdit
	@Override
	@PutMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
		groupingResgistrationService.associatePermission(groupId, permissionId);
		
		return ResponseEntity.noContent().build();
	}

}
