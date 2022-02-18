package br.com.back.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.api.assembler.GroupInputDisassembler;
import br.com.back.api.assembler.GroupingModelAssembler;
import br.com.back.api.model.GroupingModel;
import br.com.back.api.model.input.GroupInput;
import br.com.back.api.openapi.controller.GroupingControllerOpenApi;
import br.com.back.core.security.CheckSecurity;
import br.com.back.domain.model.Grouping;
import br.com.back.domain.repository.GroupingRepository;
import br.com.back.domain.service.GroupingResgistrationService;

@RestController
@RequestMapping(path = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupingController implements GroupingControllerOpenApi{

	@Autowired
	private GroupingRepository groupingRepository;
	
	@Autowired
	private GroupingResgistrationService groupingResgistrationService;
	
	@Autowired
	private GroupingModelAssembler groupingModelAssembler;
	
	@Autowired
	private GroupInputDisassembler groupInputDisassembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@Override
	@GetMapping
	public CollectionModel<GroupingModel> list(){
		List<Grouping> allGroups = groupingRepository.findAll();
		
		return groupingModelAssembler.toCollectionModel(allGroups);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@Override
	@GetMapping("/{groupId}")
    public GroupingModel search(@PathVariable Long groupId) {
		Grouping group = groupingResgistrationService.seekOrFail(groupId);
        
        return groupingModelAssembler.toModel(group);
    }
	
	@CheckSecurity.UsersGroupsPermissions.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupingModel add(@RequestBody @Valid GroupInput groupInput) {
		Grouping group = groupInputDisassembler.toDomainObject(groupInput);
		
		group = groupingResgistrationService.save(group);
		
		return groupingModelAssembler.toModel(group);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanEdit
	@Override
	@PutMapping("/{groupId}")
	public GroupingModel update(@PathVariable Long groupId,
			@RequestBody @Valid GroupInput groupInput) {
		Grouping actualGroup = groupingResgistrationService.seekOrFail(groupId);
		
		groupInputDisassembler.copyToDomainObject(groupInput, actualGroup);
		
		actualGroup = groupingResgistrationService.save(actualGroup);
		
		return groupingModelAssembler.toModel(actualGroup);
	}
	
	@CheckSecurity.UsersGroupsPermissions.CanEdit
	@Override
	@DeleteMapping("/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long groupId) {
		groupingResgistrationService.delete(groupId);
	}
	
}
