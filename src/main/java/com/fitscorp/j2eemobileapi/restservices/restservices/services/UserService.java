package com.fitscorp.j2eemobileapi.restservices.restservices.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserNotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.UserRepository;


//Service
@Service
public class UserService {

	// Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;

	// getAllUsers Method
	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	// CreateUser Method
	public User createUser(User user) throws UserExistsException{
		//if user exist using username
		User existingUser = userRepository.findByEmail(user.getEmail());
	
		//if not exists throw UserExistsException
		if(existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		
	
		return userRepository.save(user);
	}

	// getUserById
	public Optional<User> getUserByUserId(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not found in user Repository");
		}

		return user;
	}

	// updateUserById
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not found in user Repository, provide the correct user id");
		}

		
		user.setUserId(id);
		return userRepository.save(user);

	}

	// deleteUserById
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository, provide the correct user id");
		}
	
		userRepository.deleteById(id);
	}

	// getUserByUsername

	public User getUserByEmail(String emai) {
		return userRepository.findByEmail(emai);
	}

}