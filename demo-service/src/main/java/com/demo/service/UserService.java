package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.UserEntity;
import com.demo.mapper.UserMapper;

import java.util.List;

@Service("userService")
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public UserEntity getUserByUserId(int userId) {
		return userMapper.findEntityById(userId);
	}

	public int update(int userId){

		return userMapper.update(userId);
	}

	public int insert(int userId) {

		return userMapper.save(userId);
	}

	public boolean delete(int userId) {

		return userMapper.delete(userId);
	}

	public List<UserEntity> getUser(){
		return userMapper.findAll(); }
}

