package br.com.back.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.back.api.BackLinks;
import br.com.back.core.security.BackSecurity;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointerController {

	@Autowired
	private BackLinks backLinks;

	@Autowired
	private BackSecurity backSecurity;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();

		if (backSecurity.canConsultUsersGroupsPermissions()) {
			rootEntryPointModel.add(backLinks.linkToGroups("groups"));
			rootEntryPointModel.add(backLinks.linkToUsers("users"));
			rootEntryPointModel.add(backLinks.linkToPermissions("permissions"));

		}
		return rootEntryPointModel;
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}

}
