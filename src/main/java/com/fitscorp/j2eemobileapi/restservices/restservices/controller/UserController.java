package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fitscorp.j2eemobileapi.restservices.restservices.config.JwtUtil;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductWrapperDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.ApiError;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.RestResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.AuthenticationRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.RegisterRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.AuthenticationResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.Settings;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ProductService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserTokenService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private UserTokenService userTokenService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticateToken(@Valid @RequestBody AuthenticationRequest request) throws BadCredentialsException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), null, "Authorization failed to requested resource!");
//			throw new BadCredentialsException("Incorrect username or password", e);
			return new ResponseEntity<ApiError>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
		}
		
		UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
		User loggedUser = userService.getUserByEmail(request.getUsername());
		AuthenticationResponse authRes = saveUserDetailsAndGenerateAuthResponse(userDetails, loggedUser);
		
		return ResponseEntity.ok(authRes);
	}

	// Register User Method
	@PostMapping("/registrations")
	public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest request, UriComponentsBuilder builder) throws UserExistsException {
		try {
			if (userService.getUserByEmail(request.getEmail()) != null)
				throw new UserExistsException("Email address already exists");
			User user = new User();
			user.setCreated_date(new Date());
			user.setEmail(request.getEmail());
			user.setUsername(request.getEmail());
			user.setIs_email_verified(false);
			user.setName(request.getName());
			user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
			user.setPhoneNo(request.getPhoneNo());
			user.setStatus(0);
			
			User createdUser = userService.createUser(user);
			if (createdUser != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(builder.path("/users/{id}").buildAndExpand(createdUser.getUserId()).toUri());
				UserDetails userDetails = userDetailService.loadUserByUsername(createdUser.getEmail());
				AuthenticationResponse authRes = saveUserDetailsAndGenerateAuthResponse(userDetails, createdUser);
				return new ResponseEntity<AuthenticationResponse>(authRes, headers, HttpStatus.OK);
			}
			return new ResponseEntity<Object>(new RestResponse<String>(400, Arrays.asList("Retriving user failed"), null), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} catch(UserExistsException ex) {
			return new ResponseEntity<Object>(new RestResponse<String>(400, Arrays.asList("Email address already exists"), null), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public Optional<User> getUserByUserId(@PathVariable("id") Long id) {

		try {
			return userService.getUserByUserId(id);
		} catch (NotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@GetMapping("")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {

		try {
			return userService.updateUserById(id, user);
		} catch (NotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public void deleteUserByUserId(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping(path = "", params = "email")
	public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
		try {
			User user = userService.getUserByEmail(email);
			if (user == null)
				throw new NoSuchElementException();
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
	        List<String> errors = new ArrayList<String>();
	        System.out.println(errors.toString());
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	private AuthenticationResponse saveUserDetailsAndGenerateAuthResponse(UserDetails userDetails, User user) {
		// By initial design @username and @email hold the exact same value
		String refreshToken = jwtUtil.generateToken(userDetails);
		String accessToken = jwtUtil.generateToken(userDetails);
		
		UserToken token = new UserToken();
		token.setAccessToken(accessToken);
		token.setCreatedBy(user.getEmail());
		token.setCreatedDate(new Date());
		token.setEnabled(true);
		token.setStatus(1);
		token.setUserId(user.getUserId());
		user.setTokens(token);
		
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
			accessToken,
			refreshToken
		);
		
		userTokenService.saveUserToken(token);
		return authRes;
	}

	@GetMapping("/{userId}/fav-list")
	public ProductWrapperDTO getFavoriteProducts(@PathVariable Long userId) {
		try {
			return productService.getFavoriteProducts(userId);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ProductWrapperDTO(new ArrayList<>());
		}
	}
}