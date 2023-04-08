package com.hotelservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.model.Hotel;
import com.hotelservice.repo.HotelRepo;
import com.hotelservice.service.HotelService;
import com.userservice.exceptions.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomhotelId = UUID.randomUUID().toString();
		hotel.setId(randomhotelId);
		return this.hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return this.hotelRepo.findAll();
	}

	@Override
	public Hotel getHotel(String hotelId) {
		return this.hotelRepo.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!"));
	}

	@Override
	public String deleteHotel(String hotelId) {
		this.hotelRepo.deleteById(hotelId);
		return "Hotel is deleted sucessfully.";
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		this.hotelRepo.save(hotel);
		return hotel;
	}

}
