package br.com.back.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.back.api.exceptionhandler.Problem;
import br.com.back.api.model.UsersModel;
import br.com.back.api.model.input.CryptedPassInput;
import br.com.back.api.model.input.UserInput;
import br.com.back.api.model.input.UserWithPasswordInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsersControllerOpenApi {

	@ApiOperation("Lista os usuários")
    CollectionModel<UsersModel> list();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsersModel search(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long userId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
    UsersModel add(
            @ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
            UserWithPasswordInput userInput);
    
    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsersModel update(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long userId,
            
            @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados",
                required = true)
            UserInput userInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void updateCryptedPass(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long userId,
            
            @ApiParam(name = "corpo", value = "Representação de uma nova senha", 
                required = true)
            CryptedPassInput cryptedPassInput);
}
