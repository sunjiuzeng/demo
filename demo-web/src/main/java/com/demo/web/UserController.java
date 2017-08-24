package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.UserEntity;
import com.demo.service.UserService;

@RestController
@RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "get.json")
	public UserEntity getUserById(@RequestParam("userId") int userId) {
		return userService.getUserByUserId(userId);
	}

}
