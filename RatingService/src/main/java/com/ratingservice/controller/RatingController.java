package com.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ratingservice.model.Rating;
import com.ratingservice.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		
    	Rating createRating = ratingService.createRating(rating);
    	return ResponseEntity.status(HttpStatus.CREATED).body(createRating);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> allRatings = ratingService.getAllRatings();
		return ResponseEntity.ok(allRatings);
    }
    
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hotelId") String hotelId) {
		List<Rating> allRatingsOfHotel = ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok(allRatingsOfHotel);
    }
    
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId) {
		List<Rating> allRatingsOfUser = ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(allRatingsOfUser);
    }
    
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable("ratingId") String ratingId){
        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId){
        this.ratingService.deleteRating(ratingId);
    }
   
    @PutMapping("/updateRating")
    public Rating updateRating(@RequestBody Rating rating) {
    	return this.ratingService.updateRating(rating);
    }

}
