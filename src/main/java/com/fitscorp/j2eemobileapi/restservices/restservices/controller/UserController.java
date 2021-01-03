package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import com.fitscorp.j2eemobileapi.restservices.restservices.config.JwtUtil;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.FavProduct;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.InvalidCurrentPasswordException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.ApiError;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.RestResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.*;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.AuthenticationResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.ProductResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.Settings;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.TokenResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.EmailService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ProductService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserTokenService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

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
	EmailService emailService;
	
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
			final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), null, "Wrong credentials, authentication failed!");
			return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.valueOf(apiError.getStatus()));
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
			user.setPassword(userService.encodePassword(request.getPassword()));
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

	@PostMapping("/forgot-password")
	public ResponseEntity<?> handleForgotPassword(@Valid @RequestBody ForgotPasswordRequest request, UriComponentsBuilder builder) throws UserExistsException, NotFoundException {
		User user = userService.getUserByEmail(request.getEmail());
		if (user == null)
			throw new UsernameNotFoundException("User not found for the provided email address");
		String randomCode = userService.generateVerificationCode();
		emailService.sendSimpleMessage(
			request.getEmail(),
			"Jass Express password recovery",
			"Use "+ randomCode +" code to login to the app and change password"
		);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		user.setPassword_expiry(c.getTime());
		user.setPassword(userService.encodePassword(randomCode));
		userService.updateUserById(user.getUserId(), user);
		return new ResponseEntity<Object>(new RestResponse<String>(204,null, null), 
				new HttpHeaders(), 
				HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public User getUserByUserId(@PathVariable("id") Long id) {
		try {
			return userService.getUserByUserId(id);
		} catch (NotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

//	@GetMapping("")
//	public List<User> getAllUsers() {
//		return userService.getAllUsers();
//	}
	
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
		String refreshToken = jwtUtil.generateRefreshToken(userDetails);
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
		
		userTokenService.saveUserToken(token, refreshToken);
		return authRes;
	}

	@GetMapping("/{userId}/auth/extend")
	public TokenResponse extendAuth(@PathVariable Long userId,@RequestHeader(value="Authorization") String refreshToken) throws NotFoundException {
		// refreshToken coming through Authorization header param
		UserToken userToken = userTokenService.getTokenByRefreshToken(userId, refreshToken);
		User user = userService.getUserByUserId(userToken.getUserId());
		UserDetails userDetails = userDetailService
				.loadUserByUsername(user.getUsername());

		TokenResponse tokenRes = new TokenResponse(
				jwtUtil.generateToken(userDetails),
				jwtUtil.generateRefreshToken(userDetails)
		);
		userTokenService.updateUserTokenByToken(userId, tokenRes.getRefreshToken());
		return tokenRes;
	}

	@GetMapping("/{userId}/info")
	public AuthenticationResponse getUserInfo(@PathVariable Long userId) {
		try {
			User user = userService.getUserByUserId(userId);

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
					null,
					null
			);
			return authRes;
		} catch (Exception e) {
			e.printStackTrace();
			return new AuthenticationResponse();
		}
	}

	@PostMapping("/{userId}/password-change")
	public ResponseEntity<?> changePassword(@PathVariable Long userId,
			  @RequestBody ChagePasswordRequest request) throws InvalidCurrentPasswordException {
		System.out.println("User id" + userId.toString());
		if (!userService.checkIfValidOldPassword(userId, request.getCurrentPassword())) {
	        throw new InvalidCurrentPasswordException("Invalid current password");
	    }
		userService.changePassword(userId, request.getNewPassword());
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
	}
	
	// User Favorite products api endpoints

	@GetMapping("/{userId}/fav-list")
	public ProductResponse getFavoriteProducts(@PathVariable Long userId) {
		try {
			return productService.getFavoriteProducts(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProductResponse(new ArrayList<>());
		}
	}

	@PostMapping("/{userId}/fav-list")
	public List<FavProduct> addFavoriteProducts(@PathVariable Long userId, @RequestBody FavoriteRequest request) {
		try {
			return productService.addFavoriteProducts(userId, request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@DeleteMapping("/{userId}/fav-list")
	public List<FavProduct> deleteFavoriteProducts(@PathVariable Long userId, @RequestBody FavoriteRequest request) {
		try {
			return productService.deleteFavoriteProducts(userId, request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}