package com.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
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
    
    int retryCount=1;
    
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
    	logger.info("RetryCount: {}",retryCount);
    	retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    
    //creating fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
    	logger.info("Fallback is executed because service is down.",ex.getMessage());
    	User user = User.builder()
    		.email("dummy@gmail.com")
    		.name("dummy")
    		.about("This user is created dummy because service is down.")
    		.userId("dummy123456")
    		.build();
        return new ResponseEntity<>(user, HttpStatus.OK);
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
