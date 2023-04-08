package com.ratingservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.model.Rating;
import com.ratingservice.repo.RatingRepo;
import com.ratingservice.service.RatingService;
import com.userservice.exceptions.ResourceNotFoundException;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepo ratingRepo;

	@Override
	public Rating createRating(Rating rating) {
		String randomratinglId = UUID.randomUUID().toString();
		rating.setRatingId(randomratinglId);
		return this.ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return this.ratingRepo.findAll();
	}

	@Override
	public Rating getRating(String ratingId) {
		return this.ratingRepo.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!"));
	}

	@Override
	public String deleteRating(String ratingId) {
		this.ratingRepo.deleteById(ratingId);
		return "Rating is deleted sucessfully.";	
	}

	@Override
	public Rating updateRating(Rating rating) {
		this.ratingRepo.save(rating);
		return rating;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return this.ratingRepo.getRatingByHotelId(hotelId);
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return this.ratingRepo.getRatingByUserId(userId);
	}

}
