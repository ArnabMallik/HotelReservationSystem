package com.java.main;

public class ExecutiveRoom extends Room {
	
	public final static String ROOM = "Executive";
	
	public ExecutiveRoom(Double cost, String description) {
		this(roomCounter, cost, description);
		roomCounter++;
	}

	public ExecutiveRoom(Integer roomId, Double cost, String description) {
		this.setRoomId(roomId);
		this.setCost(cost);
		this.setDescription(description);
		this.setRoomType(ROOM);
	}

}
