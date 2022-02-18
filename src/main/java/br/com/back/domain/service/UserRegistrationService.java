package br.com.back.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.back.domain.exception.BusinessException;
import br.com.back.domain.exception.UserNotFoundException;
import br.com.back.domain.model.Grouping;
import br.com.back.domain.model.Users;
import br.com.back.domain.repository.UsersRepository;

@Service
public class UserRegistrationService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private GroupingResgistrationService groupingResgistrationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;  
	
	@Transactional
    public Users save(Users user) {		
		usersRepository.detach(user);
    	
    	Optional<Users> existingUser = usersRepository.findByUserEmail(user.getUserEmail());
    	
    	if(existingUser.isPresent() && !existingUser.get().equals(user)) {
    		throw new BusinessException(
    				String.format("Já existe um usuário cadastrado com o e-mail %s", user.getUserEmail()));
    	}
    	
    	if(user.isNew()) {
    		user.setCryptedPass(passwordEncoder.encode(user.getCryptedPass()));
    	}
    	
    	user.setStatus("1");
        return usersRepository.save(user);
    }
	
	@Transactional
	public void updateCryptedPass(Long userId, String actualCyptedPass, String newCyptedPass) {
		Users user = seekOrFail(userId);
		
		if(!passwordEncoder.matches(actualCyptedPass, user.getCryptedPass())) {
			throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		user.setCryptedPass(passwordEncoder.encode(newCyptedPass));
	}
	
	public Users seekOrFail(Long userId) {
		return usersRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Transactional
	public void disassociateGrouping(Long userId, Long groupingId) {
		Users user = seekOrFail(userId);
		Grouping grouping = groupingResgistrationService.seekOrFail(groupingId);
		
		user.removeGroup(grouping);		
	}
	
	@Transactional
	public void associateGrouping(Long userId, Long groupingId) {
		Users user = seekOrFail(userId);
		Grouping grouping = groupingResgistrationService.seekOrFail(groupingId);
		
		user.addGroup(grouping);
	}
	
}
