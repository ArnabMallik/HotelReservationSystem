# HotelReservationSystem

Simple program to reserve hotel rooms.
The program starts with 6 rooms in hotel. 2 Standard rooms, 2 Executive rooms and 2 Suites.
The user can perform 2 operations:-
1. Check if a room of particular type is available for booking on a particular date
2. Book a room of particular type for a given date range

The system has the following classes
1. Main.java - contains the main(). Program starts from here
2. Room.java - abstract class containing the blueprint of a generic room
3. StandardRoom.java - Extends Room and provides a standard room
4. ExecutiveRoom.java - Extends Room and provides an executive room
5. SuiteRoom.java - Extends Room and provides a Suite
6. Booking.java - Contains all information in order to book any room
7. RoomBook.java - Contains mapping between booking and room
8. Hotel.java - Contains information about all rooms available
9. ReservationTest.java - Simple junit test cases

Since no database is used, a few data structures are used to temporarily store all data during runtime.
1. List<Room> standardList - located in Hotel class. Holds all available standard rooms
2. List<Room> executiveList - located in Hotel class. Holds all available executive rooms
3. List<Room> suiteList - located in Hotel class. Holds all available suite rooms
4. Map<Integer, Room> roomMap - located in Hotel class. Holds all the rooms available (be it available / occupied). Esentially a mapping between hotelId -> Hotel object
5. Map<Integer, Booking> bookingMap - located in Booking class. Holds all the info about all bookings made. Esentially a mapping between bookingId -> Booking object
6. Map<Integer, Integer> roomBookingMap - located in RoomBook class. Its a mapping between bookingId -> roomId
