package com.java.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
	
	// these lists can be used to quickly answer if rooms are available in O(1) time
	private List<Room> standardList;
	private List<Room> executiveList;
	private List<Room> suiteList;
	private Map<Integer, Room> roomMap;  //key = room Id  value = Room
	
	private Double standardCost = 100.00, executiveCost = 200.00, suiteCost = 300.00;
	private String standardDescription = "Standard Room Type", executiveDescriprion = "Executive Room Type", suiteDescription = "Suite Room Type";
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public Hotel() {
		this.standardList = new ArrayList<Room>();
		this.executiveList = new ArrayList<Room>();
		this.suiteList = new ArrayList<Room>();
		this.roomMap = new HashMap<>();
	}
	
	public List<Room> getStandardList() {
		return standardList;
	}

	public List<Room> getExecutiveList() {
		return executiveList;
	}

	public List<Room> getSuiteList() {
		return suiteList;
	}

	public Map<Integer, Room> getRoomMap() {
		return roomMap;
	}

	// function run at startup to populate room details
	// total 6 rooms are present in hotel
	public void addRoom(String type) throws Exception {
		Room room = null;
		if(type.equals(StandardRoom.ROOM)) {
			room = new StandardRoom(standardCost, standardDescription);
			standardList.add(room);
		}else if(type.equals(ExecutiveRoom.ROOM)) {
			room = new ExecutiveRoom(executiveCost, executiveDescriprion);
			executiveList.add(room);
		}else if(type.equals(SuiteRoom.ROOM)) {
			room = new SuiteRoom(suiteCost, suiteDescription);
			suiteList.add(room);
		}else {
			throw new Exception("Invalid Room Type !!!");
		}
		roomMap.put(room.getRoomId(), room);
	}
	
	// Is a given room type available on a given date?
	public Boolean checkRoomAvailabiity(String roomType, Date date, Booking booking, RoomBook roomBook) {
		
		String stringDate = format.format(date);
		boolean overlap = true;
		
		// quickly check if room is empty, no need to check booking records
		if(roomType.equals(StandardRoom.ROOM)) {
			if(!standardList.isEmpty()) {
				printSuccess(roomType, stringDate);
				return true;
			}
		}else if(roomType.equals(ExecutiveRoom.ROOM)) {
			if(!executiveList.isEmpty()) {
				printSuccess(roomType, stringDate);
				return true;
			}
		}else {
			if(!suiteList.isEmpty()) {
				printSuccess(roomType, stringDate);
				return true;
			}
		}
		
		// if room is not empty, check booking details
		Map<Integer, Integer> roomBookMap = roomBook.getRoomBookingMap();	//key = book id, value = room id
		for(Integer bookId : roomBookMap.keySet()) {
			Integer roomId = roomBookMap.get(bookId);
			Room room = roomMap.get(roomId);
			//if the room types are same
			if(room.getRoomType().equals(roomType)) {
				//now check if booking date and queried date overlaps
				Booking tempBooking = booking.getBookingMap().get(bookId);
				if(date.getTime() < tempBooking.getCheckIn().getTime() || date.getTime() > tempBooking.getCheckout().getTime()) {
					// dates not overlapping for at least 1 booking, so room is available during given date
					overlap = false;
					break;
				}
			}
		} // all records have been seen
		if(overlap) {
			printFailure(roomType, stringDate);
			return false;
		}
			
		else {
			printSuccess(roomType, stringDate);
			return true;
		}
	}
	
	
	public Boolean bookRoom(String roomType, Date checkInDate, Date checkOutDate, Booking booking, RoomBook roomBook) {
		
		String stringCheckInDate = format.format(checkInDate);
		String stringCheckOutDate = format.format(checkOutDate);
		boolean overlap = true;
		
		// quickly check if room is empty, no need to check booking records
		if(roomType.equals(StandardRoom.ROOM)) {
			if(!standardList.isEmpty()) {
				printSuccessRange(roomType, stringCheckInDate, stringCheckOutDate);
				bookAvailableRoom(standardList, checkInDate, checkOutDate, booking, roomBook);
				return true;
			}
		}else if(roomType.equals(ExecutiveRoom.ROOM)) {
			if(!executiveList.isEmpty()) {
				printSuccessRange(roomType, stringCheckInDate, stringCheckOutDate);
				bookAvailableRoom(executiveList, checkInDate, checkOutDate, booking, roomBook);
				return true;
			}
		}else {
			if(!suiteList.isEmpty()) {
				printSuccessRange(roomType, stringCheckInDate, stringCheckOutDate);
				bookAvailableRoom(suiteList, checkInDate, checkOutDate, booking, roomBook);
				return true;
			}
		}
		
		// if room is not empty	ie - list is empty, check booking details

		Map<Integer, Integer> roomBookMap = roomBook.getRoomBookingMap();	//key = book id, value = room id
		for(Integer bookId : roomBookMap.keySet()) {
			Integer roomId = roomBookMap.get(bookId);
			Room room = roomMap.get(roomId);
			//if the room types are same
			if(room.getRoomType().equals(roomType)) {
				//now check if booking date and queried date overlaps
				Booking tempBooking = booking.getBookingMap().get(bookId);
				// (checkInDate <= booking.checkOut) and (checkOutDate >= booking.checkIn) then overlap occurs
				if(checkInDate.getTime() <= tempBooking.getCheckout().getTime() && checkOutDate.getTime() >= tempBooking.getCheckIn().getTime()) {
					// dates are overlapping, ignore this
					continue;
				}else {
					// dates are not overlapping for at least 1 booking record, new booking can be made for given date
					overlap = false;
					
					// add booking details in hashmap of Booking class
					Booking booking2 = new Booking(checkInDate, checkOutDate);
					Integer bookingId = booking2.getBookingId();
					Map<Integer, Booking> bookingMap = booking.getBookingMap();
					bookingMap.put(bookingId, booking2);
					booking.setBookingMap(bookingMap);
			
					// add booking details in hashmap of RoomBook class
					roomBookMap.put(bookingId, roomId);
					roomBook.setRoomBookingMap(roomBookMap);
					break;
				}
			}
		} // all records have been seen
		if(overlap) {
			printFailureRange(roomType, stringCheckInDate, stringCheckOutDate);
			return false;
		}	
		else {
			printSuccessRange(roomType, stringCheckInDate, stringCheckOutDate);
			return true;
		}
	}
	
	// helper method - books room when rooms are still available in List<Room>
	private void bookAvailableRoom(List<Room> roomList, Date checkInDate, Date checkOutDate, Booking booking, RoomBook roomBook) {
		 
		Room room = roomList.remove(0);
		Integer roomId = room.getRoomId();
		
		//update booking details
		Booking tempBooking = new Booking(checkInDate, checkOutDate);
		Integer bookingId = tempBooking.getBookingId();
		Map<Integer, Booking> bookingMap = booking.getBookingMap();
		bookingMap.put(bookingId, tempBooking);
		booking.setBookingMap(bookingMap);

		// add booking details in hashmap of RoomBook class
		Map<Integer, Integer> roomBookMap = roomBook.getRoomBookingMap();
		roomBookMap.put(bookingId, roomId);
		roomBook.setRoomBookingMap(roomBookMap);
	}

	
	// print availability status
	private void printSuccess(String roomType, String stringDate) {
		System.out.println(roomType + " room is available on " + stringDate);
		System.out.println("***************************************************************************");
	}
	
	// print availability status
	private void printFailure(String roomType, String stringDate) {
		System.out.println(roomType + " room is NOT available on "+ stringDate);
		System.out.println("***************************************************************************");
	}

	// print booking status
	private void printSuccessRange(String roomType, String stringCheckInDate, String stringCheckOutDate) {
		System.out.println(roomType + " room is available from " + stringCheckInDate + " to " + stringCheckOutDate);
		System.out.println("Booking Successful");
		System.out.println("***************************************************************************");
	}
	
	// print booking status
	private void printFailureRange(String roomType, String stringCheckInDate, String stringCheckOutDate) {
		System.out.println(roomType + " room is NOT available from " + stringCheckInDate + " to " + stringCheckOutDate);
		System.out.println("All " + roomType + " rooms are booked during this time. Booking failed.");
		System.out.println("***************************************************************************");
	}

}
