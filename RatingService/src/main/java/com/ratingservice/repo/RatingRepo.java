package com.ratingservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratingservice.model.Rating;

public interface RatingRepo extends JpaRepository<Rating, String>{
	
	List<Rating> getRatingByHotelId(String hotelId);
	
	List<Rating> getRatingByUserId(String userId);

}
