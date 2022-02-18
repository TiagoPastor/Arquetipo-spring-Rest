package br.com.back.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.api.assembler.UserInputDisassembler;
import br.com.back.api.assembler.UsersModelAssembler;
import br.com.back.api.model.UsersModel;
import br.com.back.api.model.input.CryptedPassInput;
import br.com.back.api.model.input.UserInput;
import br.com.back.api.model.input.UserWithPasswordInput;
import br.com.back.api.openapi.controller.UsersControllerOpenApi;
import br.com.back.core.security.CheckSecurity;
import br.com.back.domain.model.Users;
import br.com.back.domain.repository.UsersRepository;
import br.com.back.domain.service.UserRegistrationService;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController implements UsersControllerOpenApi{

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private UsersModelAssembler usersModelAssembler;
	
	@Autowired
	private UserInputDisassembler usuerInputDisassembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@GetMapping
	public CollectionModel<UsersModel> list(){
		List<Users> allUsers = usersRepository.findAll();
		
		return usersModelAssembler.toCollectionModel(allUsers);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@GetMapping("/{userId}")
	public UsersModel search(@PathVariable Long userId) {
		Users user = userRegistrationService.seekOrFail(userId);
		
		return usersModelAssembler.toModel(user);
	}
	
	@Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersModel add(@RequestBody @Valid UserWithPasswordInput userInput) {
        Users user = usuerInputDisassembler.toDomainObject(userInput);
        user = userRegistrationService.save(user);
        
        return usersModelAssembler.toModel(user);
    }
	
	@CheckSecurity.UsersGroupsPermissions.CanChangeUser
	@Override
	@PutMapping("/{userId}")
	public UsersModel update(@PathVariable Long userId,
			@RequestBody @Valid UserInput userInput){
		Users actualUser = userRegistrationService.seekOrFail(userId);
		usuerInputDisassembler.copyToDomainObject(userInput, actualUser);
		actualUser = userRegistrationService.save(actualUser);
		
		return usersModelAssembler.toModel(actualUser);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanChangeOwnPassword
	@Override
	@PutMapping("/{userId}/cryptedPass")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCryptedPass(@PathVariable Long userId, @RequestBody @Valid CryptedPassInput cryptedPass) {
		userRegistrationService.updateCryptedPass(userId, cryptedPass.getActualCryptedPass(), cryptedPass.getNewCryptedPass());
	}
	
}
