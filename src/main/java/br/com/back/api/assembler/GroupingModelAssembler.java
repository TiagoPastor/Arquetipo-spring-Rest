package br.com.back.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.back.api.BackLinks;
import br.com.back.api.controller.GroupingController;
import br.com.back.api.model.GroupingModel;
import br.com.back.core.security.BackSecurity;
import br.com.back.domain.model.Grouping;

@Component
public class GroupingModelAssembler extends RepresentationModelAssemblerSupport<Grouping, GroupingModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BackLinks backLinks;

	@Autowired
	private BackSecurity backSecurity;

	public GroupingModelAssembler() {
		super(GroupingController.class, GroupingModel.class);
	}

	@Override
	public GroupingModel toModel(Grouping grouping) {
		GroupingModel groupingModel = createModelWithId(grouping.getId(), grouping);
		modelMapper.map(grouping, groupingModel);

		if (backSecurity.canConsultUsersGroupsPermissions()) {
			groupingModel.add(backLinks.linkToGroups("groups"));

			groupingModel.add(backLinks.linkToGroupPermissions(grouping.getId(), "permissions"));
		}

		return groupingModel;
	}

	public CollectionModel<GroupingModel> toCollectionModel(Iterable<? extends Grouping> entities) {
		CollectionModel<GroupingModel> collectionModel = super.toCollectionModel(entities);

		if (backSecurity.canConsultUsersGroupsPermissions()) {
			collectionModel.add(backLinks.linkToGroups());
		}

		return collectionModel;
	}

}
