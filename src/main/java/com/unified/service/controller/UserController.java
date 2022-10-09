package com.unified.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unified.service.beeceptor.HttpPostClient;
import com.unified.service.beeceptor.UserOperationUtils;
import com.unified.service.dao.UserDao;
import com.unified.service.exception.ServiceException;
import com.unified.service.exception.UnifiedServiceException;
import com.unified.service.model.User;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private Environment env;

	private UserDao userDao;

	@Autowired
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@PostMapping("/create/user")
	public User createUser(@RequestBody User user) throws Exception {
		if(UserOperationUtils.validateUser(user, env)) {
			return userDao.save(user);
		} else {
			throw new UnifiedServiceException("User validation failed");
		}
		
	}

}
