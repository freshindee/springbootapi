package com.fitscorp.j2eemobileapi.restservices.restservices.services;


import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserNotActivatedException;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.RestResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.UserRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.AuthenticationResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;


//Service
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// getAllUsers Method
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// CreateUser Method
	public User createUser(User user) throws UserExistsException {
		//if user exist using username
		User existingUser = userRepository.findByEmail(user.getEmail());
	
		//if not exists throw UserExistsException
		if(existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}

		return userRepository.save(user);
	}

	// getUserById
	public User getUserByUserId(Long id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new NotFoundException("User does not exists!");
		}

		return user.get();
	}

	// updateUserById
	public User updateUserById(Long id, User user) throws NotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new NotFoundException("User Not found in user Repository, provide the correct user id");
		}

		user.setUserId(id);
		return userRepository.save(user);

	}

	// deleteUserById
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists!");
		}
	
		userRepository.deleteById(id);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void changePassword(Long userId, String password) {
		String encodedPassword = encodePassword(password);
		userRepository.updatePassword(userId, encodedPassword);
		return;
	}

	public String encodePassword(String password) {
		int strength = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		return encodedPassword;
	}

	public boolean checkIfValidOldPassword(Long userId, String oldPassword) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent() && user.get().getPassword() != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder.matches(oldPassword, user.get().getPassword());
		}
		throw new UsernameNotFoundException("User not found");
	}

	public String generateVerificationCode() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);
	}


	public Object getUserInfo(Long userId) {
		try {
			User user = getUserByUserId(userId);
			checkAccountActivation(user);

			AuthenticationResponse authRes = new AuthenticationResponse(
					user.getUserId(),
					user.getName(),
					user.getPassword(),
					user.getPhoneNo(),
					user.getAddress(),
					user.getEmail(),
					user.getPasswordStatus(),
					user.getStatus(),
					new Settings(new BigDecimal(30.0)),
					getUserImage(user.getUserId()),
					null,
					null
			);
			return authRes;
		} catch (UserNotActivatedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("Please activate your account!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("User does not exists!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new AuthenticationResponse();
		}
	}

	public String getUserImage(Long userId) {
		return userRepository.findImage(userId);
	}

	public boolean checkAccountActivation(Long userId) throws UserNotActivatedException, NotFoundException {
		User user = getUserByUserId(userId);
		if (!userRepository.findUserEnabled(user.getEmail())) {
			// && !userRepository.findUserEmailVerified(user.getEmail())
			throw new UserNotActivatedException("user account not activated");
		}
		return true;
	}

    public boolean checkAccountActivation(User user) throws UserNotActivatedException {
		if (user != null && !userRepository.findUserEnabled(user.getEmail())) {
			// && !userRepository.findUserEmailVerified(user.getEmail())
			throw new UserNotActivatedException("User account not activated!");
		}
		return true;
    }
}