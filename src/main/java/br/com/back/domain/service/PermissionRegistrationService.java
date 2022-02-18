package br.com.back.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.back.domain.exception.PermissionNotFoundException;
import br.com.back.domain.model.Permission;
import br.com.back.domain.repository.PermissionRepository;

@Service
public class PermissionRegistrationService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public Permission seekOrFail(Long permissionId) {
		return permissionRepository.findById(permissionId)
				.orElseThrow(() -> new PermissionNotFoundException(permissionId));
	}
	
}
