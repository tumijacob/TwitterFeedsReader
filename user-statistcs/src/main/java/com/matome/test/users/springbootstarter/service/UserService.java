package com.matome.test.users.springbootstarter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matome.test.users.springbootstarter.model.User;
import com.matome.test.users.springbootstarter.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll()
		    .forEach(users::add);
		return users;
	}
	
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	
	public User updateUser(Long id, User user) {
		return userRepository.save(user);
	}

}
