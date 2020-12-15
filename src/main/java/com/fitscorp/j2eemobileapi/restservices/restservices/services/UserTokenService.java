package com.fitscorp.j2eemobileapi.restservices.restservices.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.UserTokenRepository;


//Service
@Service
public class UserTokenService {

	// Autowire the UserRepository
	@Autowired
	private UserTokenRepository userTokenRepository;

	public List<UserToken> getAllUserTokens() {

		return userTokenRepository.findAll();

	}

	public UserToken createUserToken(UserToken userToken) throws UserExistsException{
		UserToken existingUserToken = userTokenRepository.findByUserId(userToken.getUserId());
	
		//if not exists throw UserExistsException
		if(existingUserToken != null) {
			throw new UserExistsException("UserToken already exists in repository");
		}
		
	
		return userTokenRepository.save(null);
	}

	public UserToken getUserTokenByUserId(Long id) throws NotFoundException {
		 UserToken userToken = userTokenRepository.findByUserId(id);

		if (userToken != null) {
			throw new NotFoundException("UserToken Not found in user Repository");
		}

		return userToken;
	}

	public UserToken updateUserTokenById(Long id, UserToken userToken) throws NotFoundException {
		Optional<UserToken> optionalUserToken = userTokenRepository.findById(id);

		if (!optionalUserToken.isPresent()) {
			throw new NotFoundException("UserToken Not found in user Repository, provide the correct user id");
		}

		
		userToken.setUserId(id);
		return userTokenRepository.save(userToken);

	}

	public void deleteUserTokenById(Long id) {
		Optional<UserToken> optionalUserToken = userTokenRepository.findById(id);
		if (!optionalUserToken.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository, provide the correct user id");
		}
	
		userTokenRepository.deleteById(id);
	}
}