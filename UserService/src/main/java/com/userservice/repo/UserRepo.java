package com.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.model.User;

public interface UserRepo extends JpaRepository<User, String> {

}
