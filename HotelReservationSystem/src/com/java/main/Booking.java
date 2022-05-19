package com.java.main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Booking {
	
	private static Integer bookingCounter = 1001;
	private Integer bookingId;
	private Date checkIn;
	private Date checkout;
	private Map<Integer, Booking> bookingMap = new HashMap<Integer, Booking>();  //key = booking Id  value = Booking
	
	public Booking() {

	}
	public Booking(Date checkIn, Date checkout) {
		this(bookingCounter, checkIn, checkout);
		bookingCounter++;
	}
	
	public Booking(Integer bookingId, Date checkIn, Date checkout) {
		
		this.bookingId = bookingId;
		this.checkIn = checkIn;
		this.checkout = checkout;
	}
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	
	public Map<Integer, Booking> getBookingMap() {
		return bookingMap;
	}
	public void setBookingMap(Map<Integer, Booking> bookingMap) {
		this.bookingMap = bookingMap;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", checkIn=" + checkIn + ", checkout=" + checkout + "]";
	}
	
}
