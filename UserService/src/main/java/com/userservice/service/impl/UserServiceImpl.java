package com.userservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.model.User;
import com.userservice.repo.UserRepo;
import com.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User createUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		return this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!"));
	}

	@Override
	public String deleteUser(String userId) {
		this.userRepo.deleteById(userId);
		return "User is deleted sucessfully.";
	}

	@Override
	public User updateUser(User user) {
		this.userRepo.save(user);
		return user;
	}

}
