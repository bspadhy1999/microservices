package com.hotelservice.controller;

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

import com.hotelservice.model.Hotel;
import com.hotelservice.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
			@Autowired
			private HotelService hotelService;
			
			@PostMapping("/")
		    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		    	Hotel createHotel = hotelService.createHotel(hotel);
		    	return ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
		    }
		    
		    @GetMapping("/all")
		    public ResponseEntity<List<Hotel>> getAllHotels() {
				List<Hotel> allHotels = hotelService.getAllHotels();
				return ResponseEntity.ok(allHotels);
		    }
		    
		    @GetMapping("/{hotelId}")
		    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") String hotelId){
		        Hotel hotel = hotelService.getHotel(hotelId);
		        return ResponseEntity.ok(hotel);
		    }
		
		    @DeleteMapping("/{hotelId}")
		    public void deleteHotel(@PathVariable("hotelId") String hotelId){
		        this.hotelService.deleteHotel(hotelId);
		    }
		   
		    @PutMapping("/updateHotel")
		    public Hotel updateHotel(@RequestBody Hotel hotel) {
		    	return this.hotelService.updateHotel(hotel);
		    }
	
}
