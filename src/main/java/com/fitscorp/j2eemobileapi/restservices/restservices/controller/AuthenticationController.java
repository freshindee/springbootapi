//package com.fitscorp.j2eemobileapi.restservices.restservices.controller;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fitscorp.j2eemobileapi.restservices.restservices.config.JwtUtil;
//import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
//import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;
//import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
//import com.fitscorp.j2eemobileapi.restservices.restservices.request.AuthenticationRequest;
//import com.fitscorp.j2eemobileapi.restservices.restservices.response.AuthenticationResponse;
//import com.fitscorp.j2eemobileapi.restservices.restservices.response.Settings;
//import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserService;
//import com.fitscorp.j2eemobileapi.restservices.restservices.services.UserTokenService;
//
//@RestController
//public class AuthenticationController {
//
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	UserDetailsService userDetailService;
//	
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	JwtUtil jwtUtil;
//
//	@Autowired
//	private UserTokenService userTokenService;
//	
//}