package com.ratingservice.service;

import java.util.List;

import com.ratingservice.model.Rating;

public interface RatingService {

	public Rating createRating(Rating rating);

	public List<Rating> getAllRatings();

	public Rating getRating(String ratingId);

	public String deleteRating(String ratingId);

	public Rating updateRating(Rating rating);

	public List<Rating> getRatingByHotelId(String hotelId);

	public List<Rating> getRatingByUserId(String userId);

}
