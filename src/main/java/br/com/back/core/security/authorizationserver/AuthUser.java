package br.com.back.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.back.domain.model.Users;
import lombok.Getter;

@Getter
public class AuthUser extends User{
	private static final long serialVersionUID = 1L;
	
	private Long userId;	
	private String userName;
	private String photo;
	
	public AuthUser(Users user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserEmail(), user.getCryptedPass(), authorities);
		
		this.userId = user.getId();		
		this.userName = user.getUserName();	
		this.photo = user.getPhoto();
	}
	
}
