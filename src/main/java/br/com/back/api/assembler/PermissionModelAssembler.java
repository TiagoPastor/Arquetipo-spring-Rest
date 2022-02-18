package br.com.back.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.back.api.BackLinks;
import br.com.back.api.model.PermissionModel;
import br.com.back.core.security.BackSecurity;
import br.com.back.domain.model.Permission;

@Component
public class PermissionModelAssembler 
	implements RepresentationModelAssembler<Permission, PermissionModel>{

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private BackLinks backLinks;
	
	@Autowired
	private BackSecurity backSecurity;
    
	@Override
    public PermissionModel toModel(Permission permission) {
        PermissionModel permissionModel = modelMapper.map(permission, PermissionModel.class);
        return permissionModel;
    }
    
    @Override
    public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
    	CollectionModel<PermissionModel> collectionModel 
    		= RepresentationModelAssembler.super.toCollectionModel(entities);
    	
    	if (backSecurity.canConsultUsersGroupsPermissions()) {
    		collectionModel.add(backLinks.linkToPermissions());
    	}
    	
    	return collectionModel;
    }
	
}
