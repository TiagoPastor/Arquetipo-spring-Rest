package br.com.back.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.back.domain.exception.EntityInUseException;
import br.com.back.domain.exception.GroupingNotFoundException;
import br.com.back.domain.model.Grouping;
import br.com.back.domain.model.Permission;
import br.com.back.domain.repository.GroupingRepository;

@Service
public class GroupingResgistrationService {

	private static final String MSG_GROUPING_IN_USE 
    	= "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GroupingRepository groupingRepository;
	
	@Autowired
	private PermissionRegistrationService permissionRegistrationService;
	
	@Transactional
	public Grouping save(Grouping grouping) {
		return groupingRepository.save(grouping);
	}
	
	@Transactional
	public void delete(Long groupingId) {
		try {
			groupingRepository.deleteById(groupingId);
			groupingRepository.flush();
			
		}catch (EmptyResultDataAccessException e) {
			throw new GroupingNotFoundException(groupingId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_GROUPING_IN_USE, groupingId));			
		}
	}	
	
	public Grouping seekOrFail(Long groupingId) {
		return groupingRepository.findById(groupingId)
				.orElseThrow(() -> new GroupingNotFoundException(groupingId));
	}
	
	@Transactional
	public void disassociatePermission(Long groupId, Long permissionId) {
		Grouping grouping = seekOrFail(groupId);
		Permission permission = permissionRegistrationService.seekOrFail(permissionId);
		
		grouping.removePermission(permission);
	}
	
	@Transactional
	public void associatePermission(Long groupId, Long permissionId){
		Grouping grouping = seekOrFail(groupId);
		Permission permission = permissionRegistrationService.seekOrFail(permissionId);
		
		
		grouping.addPermission(permission);
	}
	
}
