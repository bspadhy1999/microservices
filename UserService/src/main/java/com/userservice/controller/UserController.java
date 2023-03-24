package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.model.User;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	User createUser = userService.createUser(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userService. getAllUsers();
		return ResponseEntity.ok(allUsers);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        this.userService.deleteUser(userId);
    }
   
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
    	return this.userService.updateUser(user);
    }

}
