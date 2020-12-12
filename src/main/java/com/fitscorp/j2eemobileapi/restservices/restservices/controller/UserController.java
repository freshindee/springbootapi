package com.fitscorp.j2eemobileapi.restservices.restservices.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserNotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserService;



//Controller -
@RestController
public class UserController {

	// Autowire the UserService
	@Autowired
	private UserService userService;

	// getAllUsers Method
	@GetMapping("/users")
	public List<User> getAllUsers() {

		return userService.getAllUsers();

	}

	// Create User Method
	// @RequestBody Annotation
	// @PostMapping Annotation
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// getUserById
	@GetMapping("/users/{id}")
	public Optional<User> getUserByUserId(@PathVariable("id") Long id) {

		try {
			return userService.getUserByUserId(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	// updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {

		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	// deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserByUserId(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	// getUserByEmail
	@GetMapping(path = "/users", params = "email")
	public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
		try {
			User user = userService.getUserByEmail(email);
			if (user == null)
				throw new NoSuchElementException();
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
	        List<String> errors = new ArrayList<String>();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
}