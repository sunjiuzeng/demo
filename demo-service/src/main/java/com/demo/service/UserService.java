package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.UserEntity;
import com.demo.mapper.UserMapper;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public UserEntity getUserByUserId(int userId) {
		return userMapper.findEntityById(userId);
	}

}
