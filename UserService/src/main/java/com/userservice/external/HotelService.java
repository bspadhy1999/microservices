package com.userservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.userservice.model.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotel/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
	@PostMapping("/hotel/")
	public Hotel createHotel(@RequestBody Hotel hotel);
	
	@PutMapping("/hotel/updateHotel")
	public Hotel updateHotel(@RequestBody Hotel hotel);
	
	@DeleteMapping("/{hotelId}")
	public void deleteHotel(@PathVariable("hotelId") String hotelId);
	
}
