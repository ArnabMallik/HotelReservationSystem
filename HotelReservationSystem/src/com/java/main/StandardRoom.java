package com.java.main;

public class StandardRoom extends Room {
	
	public final static String ROOM = "Standard";
	
	public StandardRoom(Double cost, String description) {
		this(roomCounter, cost, description);
		roomCounter++;
	}

	public StandardRoom(Integer roomId, Double cost, String description) {
		this.setRoomId(roomId);
		this.setCost(cost);
		this.setDescription(description);
		this.setRoomType(ROOM);
	}

}
