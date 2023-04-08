package com.userservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.external.HotelService;
import com.userservice.model.Hotel;
import com.userservice.model.Rating;
import com.userservice.model.User;
import com.userservice.repo.UserRepo;
import com.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

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
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!"));
		 Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList=ratings.stream().map(rating -> {
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();
			//logger.info("response status code:{}", forEntity.getStatusCode());
			Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRating(ratingList);
		return user;
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
