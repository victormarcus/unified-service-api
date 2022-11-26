package com.unified.service.controller;

import com.unified.service.model.LoginRequest;
import com.unified.service.model.LoginResponse;
import com.unified.service.services.UserDetailsServiceImpl;
import com.unified.service.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.unified.service.beeceptor.UserOperationUtils;
import com.unified.service.dao.UserDao;
import com.unified.service.exception.UnifiedServiceException;
import com.unified.service.model.UserModel;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

//	@Autowired
//	private Environment env;



	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDao userDao;


	@Autowired
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * let's check the spring security try first without jwt token then try with jwt token with this endpoint
	 * @return string
	 */
	@GetMapping("/hello")
	public String getHello(){
		return "Hello from the other side";
	}

	/**
	 * @param loginRequest
	 * This method will first authenticate user credential with the help of spring security
	 * UsernamePasswordAuthenticationToken method
	 * for more info check https://www.baeldung.com/manually-set-user-authentication-spring-security
	 * if it verifies then we will return jwt token
	 * @throws BadCredentialsException
	 * @return jwt token
	 */
	@PostMapping("/login")
	public ResponseEntity<?>createAuthenticationToken(@RequestBody LoginRequest loginRequest)
		throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		}
		catch (BadCredentialsException e){
			throw new Exception("Incorrect username or password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
		final String jwt = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new LoginResponse(jwt));
	}


	/**
	 * @Param UserModel
	 * We have to  encode password using Bcrypt module of spring security
	 * for more info check https://www.baeldung.com/spring-security-5-default-password-encoder
	 * throws Exception
	 * @return String
	 */
	//
	@PostMapping("/create/user")
	public String createUser(@Valid @RequestBody UserModel user, BindingResult result) throws Exception {
//		if(UserOperationUtils.validateUser(userModel, env)) {
//			return userDao.save(userModel);
//		} else {
//			throw new UnifiedServiceException("User validation failed");
//		}
		if(result.hasErrors()){
			throw new UnifiedServiceException("Username or Password is not in correct format.");
		}
		System.out.println("Encrypting Password");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		userDao.save(user);
		return "Registered Successfully";

	}

}
