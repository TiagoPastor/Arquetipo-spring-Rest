package br.com.back.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.back.api.exceptionhandler.Problem;
import br.com.back.api.model.GroupingModel;
import br.com.back.api.model.input.GroupInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GroupingControllerOpenApi {

	@ApiOperation("Lista os grupos")
	CollectionModel<GroupingModel> list();
	
	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	GroupingModel search(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);
	
	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado"),
	})
	GroupingModel add(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
			GroupInput groupInput);
	
	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	GroupingModel update(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId,
			
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", 
				required = true)
			GroupInput groupInput);
	
	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);
	
}
