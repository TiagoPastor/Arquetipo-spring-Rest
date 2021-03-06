package br.com.back.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.back.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
