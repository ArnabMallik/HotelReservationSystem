package com.java.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.main.Booking;
import com.java.main.Hotel;
import com.java.main.Room;
import com.java.main.RoomBook;
import com.java.main.StandardRoom;

class ReservationTest {
	
	SimpleDateFormat format;
	Hotel myHotel;
	Booking booking;
	RoomBook roomBook;
	List<Room> standardList;
	
	@BeforeEach
	public void setup() throws Exception {
		
		// 2 standard rooms are available in hotel
		// booking 2 standard rooms from 18 May 2022 to 20 May 2022
		format = new SimpleDateFormat("yyyy-MM-dd");
		myHotel = new Hotel();
		booking = new Booking();
		roomBook = new RoomBook();
		standardList = myHotel.getStandardList();
		
		myHotel.addRoom(StandardRoom.ROOM);
		myHotel.addRoom(StandardRoom.ROOM);
		
		Date checkInDate = new Date();
		checkInDate = format.parse("2022-05-18");
		Date checkOutDate = new Date();
		checkOutDate = format.parse("2022-05-20");
		myHotel.bookRoom("Standard", checkInDate, checkOutDate, booking, roomBook);
		
		checkInDate = new Date();
		checkInDate = format.parse("2022-05-18");
		checkOutDate = new Date();
		checkOutDate = format.parse("2022-05-20");
		myHotel.bookRoom("Standard", checkInDate, checkOutDate, booking, roomBook);

	}
		
	@Test
	public void testDataStructureSize() throws Exception {
					
		assertTrue("There must be 0 standard rooms available now in standardList", standardList.size() == 0);
		assertTrue("There must be 2 records present in bookingMap", booking.getBookingMap().size() == 2);
		assertTrue("There must be 2 records present in roomBookingMap", roomBook.getRoomBookingMap().size() == 2);
		
		Date checkInDate = new Date();
		checkInDate = format.parse("2022-05-18");
		Date checkOutDate = new Date();
		checkOutDate = format.parse("2022-05-20");
		myHotel.bookRoom("Standard", checkInDate, checkOutDate, booking, roomBook);
		assertTrue("Overlapping dates so booking should fail", booking.getBookingMap().size() == 2);
	}
	
	@Test
	public void testRoomAvailabiity() throws Exception {
		
		Date date = new Date();
		date = format.parse("2022-05-18");
		Boolean b = myHotel.checkRoomAvailabiity("Standard", date, booking, roomBook);
		assertFalse("Room should not be available", b);
		
		date = format.parse("2022-05-17");
		b = myHotel.checkRoomAvailabiity("Standard", date, booking, roomBook);
		assertTrue("Room should be available", b);
		
		date = format.parse("2022-05-19");
		b = myHotel.checkRoomAvailabiity("Standard", date, booking, roomBook);
		assertFalse("Room should not be available", b);
		
		date = format.parse("2022-05-20");
		b = myHotel.checkRoomAvailabiity("Standard", date, booking, roomBook);
		assertFalse("Room should not be available", b);
		
		date = format.parse("2022-05-21");
		b = myHotel.checkRoomAvailabiity("Standard", date, booking, roomBook);
		assertTrue("Room should be available", b);
	}
	
	@Test
	public void testBookRoom() throws Exception {
		
		Date checkInDate = new Date();
		checkInDate = format.parse("2022-05-18");
		Date checkOutDate = new Date();
		checkOutDate = format.parse("2022-05-20");
		Boolean b = myHotel.bookRoom("Standard", checkInDate, checkOutDate, booking, roomBook);
		assertFalse("Booking should not be sucessful", b);
		
		checkInDate = new Date();
		checkInDate = format.parse("2022-05-25");
		checkOutDate = new Date();
		checkOutDate = format.parse("2022-05-30");
		b = myHotel.bookRoom("Standard", checkInDate, checkOutDate, booking, roomBook);
		assertTrue("Booking should be sucessful", b);
		
	}

}
