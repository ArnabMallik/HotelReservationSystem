package com.java.main;

public abstract class Room {
	
	protected static Integer roomCounter = 101;
	private Integer roomId;
	private Double cost;
	private String description;
	private String roomType;
	
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", cost=" + cost + ", description=" + description + ", roomType=" + roomType
				+ "]";
	}
	
}
