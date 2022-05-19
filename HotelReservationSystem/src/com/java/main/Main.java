package com.java.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) {
		
		Hotel myHotel = new Hotel();
		Booking booking = new Booking();
		RoomBook roomBook = new RoomBook();
		
		//create 2 rooms of each type. So total 6 rooms in hotel
		try {
			myHotel.addRoom(StandardRoom.ROOM);
			myHotel.addRoom(StandardRoom.ROOM);
			myHotel.addRoom(ExecutiveRoom.ROOM);
			myHotel.addRoom(ExecutiveRoom.ROOM);
			myHotel.addRoom(SuiteRoom.ROOM);
			myHotel.addRoom(SuiteRoom.ROOM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//taking input from user
		
		while(true) {
			//printAllDataStructures(myHotel, booking, roomBook);
			System.out.println();
			System.out.println("***************************************************************************");
			System.out.println("Enter Choice: ");
			System.out.println("1 : Check Room Availability");
			System.out.println("2 : Book Room");
			System.out.println("Press any other key to exit");
			System.out.println("***************************************************************************");
			try {
				String str = br.readLine();
				switch (str) {
					case "1": {
						String roomType = getRoomTypeFromUser();
						System.out.println("Enter date (yyyy-MM-dd) :");
						Date date = new Date();
						String enteredDate = br.readLine();
						if(!isValidDate(enteredDate)) {
							System.out.println("Invalid date ... Start over");
							continue;
						}
						date = format.parse(enteredDate);
						myHotel.checkRoomAvailabiity(roomType, date, booking, roomBook);
						break;
					}
					case "2": {
						String roomType = getRoomTypeFromUser();
						System.out.println("Enter check-in date (yyyy-MM-dd) :");
						Date checkInDate = new Date();
						String enteredDate = br.readLine();
						if(!isValidDate(enteredDate)) {
							System.out.println("Invalid date ... Start over");
							continue;
						}
						checkInDate = format.parse(enteredDate);
						System.out.println("Enter check-out date (yyyy-MM-dd) :");
						Date checkOutDate = new Date();
						enteredDate = br.readLine();
						if(!isValidDate(enteredDate)) {
							System.out.println("Invalid date ... Start over");
							continue;
						}
						checkOutDate = format.parse(enteredDate);
						if(checkOutDate.getTime() < checkInDate.getTime()) {
							System.out.println("Checkout date must be after Checkin date ... Start over");
							continue;
						}
						myHotel.bookRoom(roomType, checkInDate, checkOutDate, booking, roomBook);
						break;
					}
					default:
						return;
				}
			} catch (IOException e) {
				System.out.println("Invalid Input " + e);
				System.out.println("Start over ...");
				continue;
			} catch (ParseException e) {
				System.out.println("Invalid Date Format " + e);
				System.out.println("Start over ...");
				continue;
			} catch (NumberFormatException e) {
				System.out.println("Invalid Number " + e);
				System.out.println("Start over ...");
				continue;
			}catch (Exception e) {
				System.out.println("Exception " + e);
				System.out.println("Start over ...");
				continue;
			}
		}
	}
	
	// check if entered date is valid
	private static boolean isValidDate(String enteredDate) {
		boolean valid = false; 
		try {
			// strict mode - check 30 or 31 days, leap year
		 		format.setLenient(false);
				@SuppressWarnings("unused")
				Date parsedDate = format.parse(enteredDate);
	            valid = true;

	        } catch (ParseException e) {
	            //e.printStackTrace();
	            valid = false;
	        }
		return valid;
	}

	// helper function to view the contents in data structures
	@SuppressWarnings("unused")
	private static void printAllDataStructures(Hotel hotel, Booking booking, RoomBook roomBook) {
		
		System.out.println("StandardList :");
		System.out.println(hotel.getStandardList());
		System.out.println("ExecutiveList :");
		System.out.println(hotel.getExecutiveList());
		System.out.println("SuiteList :");
		System.out.println(hotel.getSuiteList());
		System.out.println("RoomMap :");
		System.out.println(hotel.getRoomMap());
		System.out.println("BookingMap :");
		System.out.println(booking.getBookingMap());
		System.out.println("RoomBookingMap :");
		System.out.println(roomBook.getRoomBookingMap());
		System.out.println("***************************************************************************");
	}

	// helper function to print room details
	private static String getRoomTypeFromUser() throws NumberFormatException, IOException, Exception {
		
		System.out.println();
		System.out.println("Room types are : " + StandardRoom.ROOM + ", " + ExecutiveRoom.ROOM +", and " + SuiteRoom.ROOM);
		System.out.println("Select Room Type : ");
		System.out.println("1 : "+ StandardRoom.ROOM + " Room");
		System.out.println("2 : "+ ExecutiveRoom.ROOM + " Room");
		System.out.println("3 : "+ SuiteRoom.ROOM + " Room");
		Integer type = Integer.parseInt(br.readLine());
			String roomType = "";
			if(type == 1)
				roomType = StandardRoom.ROOM;
			else if(type == 2)
				roomType = ExecutiveRoom.ROOM;
			else if(type == 3)
				roomType = SuiteRoom.ROOM;
			else
				throw new Exception("Invalid Room Type !!!");
			return roomType;
	}

}
