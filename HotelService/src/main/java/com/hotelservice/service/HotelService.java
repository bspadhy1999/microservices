package com.hotelservice.service;

import java.util.List;

import com.hotelservice.model.Hotel;

public interface HotelService {

	public Hotel createHotel(Hotel hotel);

	public List<Hotel> getAllHotels();

	public Hotel getHotel(String hotelId);

	public String deleteHotel(String hotelId);

	public Hotel updateHotel(Hotel hotel);

}
