package br.com.back.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import br.com.back.api.model.GroupingModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruopsModel")
@Data
public class GroupsModelOpenApi {

	private GroupsEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("GruposEmbeddedModel")
    @Data
    public class GroupsEmbeddedModelOpenApi {
        
        private List<GroupingModel> grupos;
        
    }   
}
