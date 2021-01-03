package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserTokenService {

	// Autowire the UserRepository
	@Autowired
	private UserTokenRepository userTokenRepository;

	public List<UserToken> getAllUserTokens() {

		return userTokenRepository.findAll();

	}

	public UserToken saveUserToken(UserToken userToken, String refreshToken) {
//		UserToken existingUserToken = userTokenRepository.findByUserId(userToken.getUserId());
//	
//		//if not exists throw UserExistsException
//		if(existingUserToken == null) {
//			throw new UserExistsException("UserToken already exists in repository");
//		}
		UserToken uToken = userTokenRepository.save(userToken);
		userTokenRepository.updateTokenByRefreshToken(uToken.getUserId(), refreshToken);
		return uToken;
	}

	public UserToken getUserTokenByUserId(Long id) throws NotFoundException {
		 UserToken userToken = userTokenRepository.findByUserId(id);

		if (userToken == null) {
			throw new NotFoundException("UserToken Not found in user Repository");
		}

		return userToken;
	}

	public UserToken getTokenByRefreshToken(Long userId, String token) throws NotFoundException {
		// Removing Bearer:: doesn't matter either not exist because this will throw not found if it does n;t
		UserToken userToken = userTokenRepository.findTokenByRefreshToken(userId, token.substring(7));

		if (userToken == null) {
			throw new UsernameNotFoundException("UserToken Not found in user Repository");
		}

		return userToken;
	}

	public Integer updateUserTokenByToken(Long userId, String userToken) {
		userTokenRepository.updateTokenByRefreshToken(userId, userToken);
		return 1;

	}

	public UserToken updateUserTokenById(Long id, UserToken userToken) throws NotFoundException {
		Optional<UserToken> optionalUserToken = userTokenRepository.findById(id);

		if (!optionalUserToken.isPresent()) {
			throw new NotFoundException("UserToken Not found in user Repository, provide the correct user id");
		}

		
		userToken.setId(id);
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