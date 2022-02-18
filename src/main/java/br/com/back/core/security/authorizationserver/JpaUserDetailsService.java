package br.com.back.core.security.authorizationserver;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.back.domain.model.Users;
import br.com.back.domain.repository.UsersRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Users user = usersRepository.findByUserEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));
		
		return new AuthUser(user, getAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Users user){		
		return user.getGroupings().stream()
				.flatMap(grouping -> grouping.getPermissions().stream())
				.map(permission -> new SimpleGrantedAuthority(permission.getName().toUpperCase()))
				.collect(Collectors.toSet());
	}

}
