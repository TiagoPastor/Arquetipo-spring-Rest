package br.com.back.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.back.api.model.input.UserInput;
import br.com.back.domain.model.Users;

@Component
public class UserInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Users toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, Users.class);
	}
	
	public void copyToDomainObject(UserInput userInput, Users users) {
		modelMapper.map(userInput, users);
	}

}
