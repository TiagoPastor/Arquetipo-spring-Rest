package br.com.back.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.back.api.model.input.GroupInput;
import br.com.back.domain.model.Grouping;

@Component
public class GroupInputDisassembler {
	 
	@Autowired
    private ModelMapper modelMapper;
	
	public Grouping toDomainObject(GroupInput groupInput) {
		return modelMapper.map(groupInput, Grouping.class);
	}
	
	public void copyToDomainObject(GroupInput groupInput, Grouping grouping) {
		modelMapper.map(groupInput, grouping);
	}

}
