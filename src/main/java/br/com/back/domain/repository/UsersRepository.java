package br.com.back.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.back.domain.model.Users;

@Repository
public interface UsersRepository extends CustomJpaRepository<Users, Long>{	

	Optional<Users> findByUserEmail(String email);
	
}
