package com.java.main;

public class SuiteRoom extends Room {
	
	public final static String ROOM = "Suite";
	
	public SuiteRoom(Double cost, String description) {
		this(roomCounter, cost, description);
		roomCounter++;
	}

	public SuiteRoom(Integer roomId, Double cost, String description) {
		this.setRoomId(roomId);
		this.setCost(cost);
		this.setDescription(description);
		this.setRoomType(ROOM);
	}

}
