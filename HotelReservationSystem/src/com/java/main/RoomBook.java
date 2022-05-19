package com.java.main;

import java.util.HashMap;
import java.util.Map;

public class RoomBook {
	
	private Map<Integer, Integer> roomBookingMap = new HashMap<Integer, Integer>();		//key = book id, value = room id

	public RoomBook() {
	}
	
	public RoomBook(Integer bookId, Integer roomId) {
		roomBookingMap.put(bookId, roomId);
	}

	public Map<Integer, Integer> getRoomBookingMap() {
		return roomBookingMap;
	}

	public void setRoomBookingMap(Map<Integer, Integer> roomBookingMap) {
		this.roomBookingMap = roomBookingMap;
	}

}
