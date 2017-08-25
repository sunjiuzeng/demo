package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.UserEntity;
import com.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "get.json")
	public UserEntity getUserById(@RequestParam("userId") int userId) {
		return userService.getUserByUserId(userId);
	}

	@RequestMapping(value = "change")
	public int update(@RequestParam("userId") int userId) {
		return userService.update(userId);
	}

	@RequestMapping(value = "save")
	public int insert(@RequestParam("userId") int userId) {
		return userService.insert(userId);
	}

	@RequestMapping(value = "delete")
	public boolean delete(@RequestParam("userId") int userId){
		return userService.delete(userId);
	}

	@RequestMapping(value = "list")
	public List<UserEntity> findAll(){ return userService.getUser(); }
}
