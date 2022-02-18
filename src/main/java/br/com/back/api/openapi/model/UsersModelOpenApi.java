package br.com.back.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import br.com.back.api.model.UsersModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("UsersModel")
@Data
public class UsersModelOpenApi {

	 private UsersEmbeddedModelOpenApi _embedded;
	    private Links _links;
	    
	    @ApiModel("UsuariosEmbeddedModel")
	    @Data
	    public class UsersEmbeddedModelOpenApi {
	        
	        private List<UsersModel> usuarios;
	        
	    }   
	} 