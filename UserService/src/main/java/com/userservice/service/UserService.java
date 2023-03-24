package com.userservice.service;

import java.util.List;

import com.userservice.model.User;

public interface UserService {

	public User createUser(User user);

	public List<User> getAllUsers();

	public User getUser(String userId);

	public String deleteUser(String userId);

	public User updateUser(User user);

}
