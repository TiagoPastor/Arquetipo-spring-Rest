package br.com.back.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import br.com.back.api.model.PermissionModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PermissionModel")
@Data
public class PermissionsModelOpenApi {

	private PermissionsEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissionsEmbeddedModelOpenApi {
        
        private List<PermissionModel> permissoes;
        
    }   
}
