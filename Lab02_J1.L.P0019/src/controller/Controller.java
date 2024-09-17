package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import dao.BoardingPassDaoImpl;
import model.Crew;
import model.Flight;
import dao.FlightDaoImpl;
import model.Reservation;
import dao.ReservationDaoImpl;
import java.io.IOException;
import utils.Validation;


public class Controller implements IController {

    private final String DATE_TIME_FORMAT = "dd-MM-yyyy-HH:mm";
    private final String DATE_FORMAT = "dd-MM-yyyy";
    private final String FLIGHT_ID_FORMAT = "^F\\d{4}$";
    private final String RESERVATION_ID_FORMAT = "^R\\d{7}$";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    FlightDaoImpl flightManager;
    ReservationDaoImpl reservationManager;
    BoardingPassDaoImpl boardingPassManager;
    Validation validation;

    
    public Controller(){
        this.boardingPassManager = new BoardingPassDaoImpl();
        this.validation = new Validation();
        this.flightManager = new FlightDaoImpl();
        this.reservationManager = new ReservationDaoImpl(); 
    }

    private Flight inputFlight() {
        String flightNumber;
        do {
            flightNumber = validation.getID("Enter flight number (Should be Fxxxx and no space): ", "Should be Fxxxx and should not be blank, please try again", FLIGHT_ID_FORMAT, false);
            if (flightManager.containsKey(flightNumber)) {
                System.out.println("Flight number already exists. Please enter a unique flight number.");
            }
        } while (flightManager.containsKey(flightNumber));

        String departureCity = validation.getString("Enter departure city: ", "Should not be blank, please try again", false);
        String destinationCity = validation.getString("Enter destination city: ", "Should not be blank, please try again", false);
        Date departureTime;
        Date arrivalTime;

        while (true) {
            departureTime = validation.getDate("Enter departure time (format dd-MM-yyyy-HH:mm): ", "Should follow format and should not be blank, please try again", DATE_TIME_FORMAT, false);
            arrivalTime = validation.getDate("Enter arrival time (format dd-MM-yyyy-HH:mm): ", "Should follow format and should not be blank, please try again", DATE_TIME_FORMAT, false);

            // Check if dates are valid
            if (!(validation.areDatesValid(departureTime, arrivalTime))) {
                System.out.println("Invalid date range. departure time should not be after the arrival time. Please try again.");
            } else {
                break;
            }
        }
        int availableSeat = validation.getInteger("Enter number of available seats: ", "Invalid, should be a number, please try again", false);
        return new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, availableSeat);
    }

    private Flight inputFlightForUpdate(String flightNumber) { //Blank input for unchanged information
        String departureCity = validation.getString("Enter departure city: ", "Should not be blank, please try again", false);
        String destinationCity = validation.getString("Enter destination city: ", "Should not be blank, please try again", false);
        Date departureTime;
        Date arrivalTime;

        while (true) {
            departureTime = validation.getDate("Enter departure time (format dd-MM-yyyy-HH:mm): ", "Should follow format and should not be blank, please try again", DATE_TIME_FORMAT, false);
            arrivalTime = validation.getDate("Enter arrival time (format dd-MM-yyyy-HH:mm): ", "Should follow format and should not be blank, please try again", DATE_TIME_FORMAT, false);

            // Check if dates are valid
            if (!(validation.areDatesValid(departureTime, arrivalTime))) {
                System.out.println("Invalid date range. departure time should not be after the arrival time. Please try again.");
            } else {
                break;
            }
        }
        int availableSeat = validation.getInteger("Enter number of available seats: ", "Invalid, should be a number, please try again", false);
        return new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, availableSeat);
    }

    private Reservation inputReservation(String flightNumber) {
        String name = validation.getString("Enter name: ", "Should not be blank, please try again", false);
        String address = validation.getString("Enter address: ", "Should not be blank, please try again", false);
        String phoneNumber = validation.getString("Enter phone number: ", "Should not be blank, please try again", false);
        String identityCardNumber = validation.getString("Enter identity card number: ", "Should not be blank, please try again", false);
        return new Reservation(name, address, phoneNumber, identityCardNumber, flightNumber);
    }
    
    private Crew inputCrew() {
        String name = validation.getString("Enter crew member's name: ", "Should not be blank, please try again", false);
        Crew.Position position = null;

        while (position == null) {
            int positionChoice = validation.getInteger("Select crew member's position:\n1. Pilot\n2. Flight Attendant\n3. Ground Staff\nEnter the corresponding number: ", "Invalid choice, please try again", false);

            switch (positionChoice) {
                case 1:
                    position = Crew.Position.PILOT;
                    break;
                case 2:
                    position = Crew.Position.ATTENDANT;
                    break;
                case 3:
                    position = Crew.Position.GROUNDSTAFF;
                    break;
                default:
                    // Handle invalid input
                    System.out.println("Invalid position choice. Please select a valid position.");
                    break;
            }
        }

        return new Crew(name, position);
    }

    @Override
    public void addFlight() {
        Flight newFlight = inputFlight();
        flightManager.addFlight(newFlight.getFlightNumber(), newFlight);
        System.out.println("Flight added successfully!");
    }

    @Override
    public void getFlight() {
        String flightNumber = validation.getID("Enter flight number (Should be Fxxxx and no space): ", "Should be Fxxxx and should not be blank, please try again", FLIGHT_ID_FORMAT, false);
        System.out.println(flightManager.getFlight(flightNumber));
    }

    @Override
    public void updateFlight() {
        String flightNumber = validation.getID("Enter flight number (Should be Fxxxx and no space): ", "Should be Fxxxx and should not be blank, please try again", FLIGHT_ID_FORMAT, false);

        if (!flightManager.containsKey(flightNumber)) {
            System.out.println("Flight number does not exist. Cannot update.");
            return;
        }

        Flight updateFlight = inputFlightForUpdate(flightNumber);
        flightManager.updateFlight(flightNumber, updateFlight);
        System.out.println("Flight updated successfully!");
    }

    @Override
    public void removeFlight() {
        String flightNumber = validation.getID("Enter flight number (Should be Fxxxx and no space): ", "Should be Fxxxx and should not be blank, please try again", FLIGHT_ID_FORMAT, false);

        if (!flightManager.containsKey(flightNumber)) {
            System.out.println("Flight number does not exist. Cannot update.");
            return;
        }

        if (validation.confirmYesNo("Are you sure to remove the flight? Enter Y/N: ")) {
            flightManager.removeFlight(flightNumber);
        } else {
            System.out.println("Removing cancelled");
        }
    }

    @Override
    public void showAllFlights() {
        flightManager.showAllFlights();
    }

    @Override
    public void addReservation() {
        // The adding should follow the current available seats of that flight

        String keyword = validation.getString("Enter keyword to search (can be departure city or time, destination city, arrival time)\nIf the keyword is a date, please follow the pattern dd-MM-yyyy for accurate results: ", "Cannot be blank, please try again", false);
        HashMap<String, Flight> foundFlights = flightManager.getFlightsByKeyword(keyword);

        if (foundFlights.isEmpty()) {
            System.out.println("No flight found");
        } else {
            System.out.println("Found Flights:");

            // Use the getSelectedFlight method to select a flight
            Flight selectedFlight = getSelectedFlight(foundFlights);

            if (reservationManager.getReservationsForFlight(selectedFlight.getFlightNumber()).size() == selectedFlight.getAvailableSeat()) {
                System.out.println("Available slots for the flight are running out. Cannot add a reservation.");
            } else {
                Reservation reservation = inputReservation(selectedFlight.getFlightNumber());
                reservationManager.addReservation(reservation.getReservationID(), reservation);
                System.out.println("Reservation ID: " + reservation.getReservationID() + " added successfully.");
            }
        }
    }

    @Override
    public void flightCheckIn() {
        String reservationID = validation.getID("Enter your reservation ID: ", "Should follow format Rxxxxxxx (7 numbers after) and should not be blank, please try again", RESERVATION_ID_FORMAT, false);
        Reservation reservation = reservationManager.getReservation(reservationID);

        if (reservation == null) {
            System.out.println("No such reservation ID found!");
            return;
        }
        
        if (reservation.getBoardingPassSeatNumber() == null){
            System.out.println("Seat not yet allocated for this reservation, cannot generate boarding pass");
            return;
        }
        

        generateBoardingPass(reservation);
        System.out.println("Check-in successful! Boarding pass generated.");
    }
    
    @Override
    public void selectSeat() {
        String reservationID = validation.getID("Enter your reservation ID: ", "Should follow format Rxxxxxxx (7 numbers after) and should not be blank, please try again", RESERVATION_ID_FORMAT, false);
        Reservation reservation = reservationManager.getReservation(reservationID);

        if (reservation == null) {
            System.out.println("No such reservation ID found!");
            return;
        }

        if (reservation.getBoardingPassSeatNumber() != null) {
            System.out.println("This reservation is already assigned a seat (Seat Number: " + reservation.getBoardingPassSeatNumber() + "). Cannot check-in again.");
            return;
        }

        Flight flight = flightManager.getFlight(reservation.getReservationFlightNumber());

        if (flight == null) {
            System.out.println("No such flight found for the reservation.");
            return;
        }

        LinkedHashMap<String, Boolean> seats = flight.getSeatList();

        if (seats == null) {
            System.out.println("No seat information available for this flight.");
            return;
        }

        System.out.println("Available seats for flight " + flight.getFlightNumber() + ": ");
        showSeatsMap(seats);

        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            String selectedSeat = validation.getString("Enter the seat number you want to choose: ", "Seat number should not be blank, please try again", false);

            if (seats.containsKey(selectedSeat)) {
                if (seats.get(selectedSeat)) {
                    seats.put(selectedSeat, false); // Mark the selected seat as unavailable
                    reservation.setBoardingPassSeatNumber(selectedSeat); // Set the selected seat for the reservation
                    reservationManager.updateReservation(reservationID, reservation); // Update the reservation with the selected seat
                    System.out.println("Seat assigned successful!");
                    break; // Exit the loop after a successful seat assignment
                } else {
                    System.out.println("Selected seat is already taken. Please choose another seat.");
                }
            } else {
                System.out.println("Invalid seat number. Please choose a valid seat.");
            }

            attempts++;

            if (attempts >= maxAttempts) {
                System.out.println("Maximum attempts reached. Seat assignment canceled.");
            }
        }
    }

    @Override
    public void assignCrewToFlight() {
        String flightNumber = validation.getID("Enter flight number (Should be Fxxxx and no space): ", "Should be Fxxxx and should not be blank, please try again", FLIGHT_ID_FORMAT, false);

        if (!flightManager.containsKey(flightNumber)) {
            System.out.println("Flight number does not exist. Cannot assign crew.");
            return;
        }

        List<Crew> crewMembers = new ArrayList<>();
        int numCrewMembers = validation.getInteger("Enter the number of crew members to assign: ", "Invalid, should be a number, please try again", false);

        for (int i = 0; i < numCrewMembers; i++) {
            Crew crewMember = inputCrew();

            crewMembers.add(crewMember);
        }

        Flight flight = flightManager.getFlight(flightNumber);
        flight.assignCrew(crewMembers);
        System.out.println("Crew members assigned to the flight successfully!");
        System.out.println(flight);
    }
    
    @Override
    public void showFlightsInSpecificDepartureDate(){
        String keyword = validation.getString("Enter departure date to show\nPlease follow the pattern dd-MM-yyyy for accurate results: ", "Cannot be blank, please try again", false);
        if (flightManager.getFlightsByKeyword(keyword) == null){
            System.out.println("No flight found!");
            return;
        }
        for (Flight flight : flightManager.getFlightsByKeyword(keyword).values()) {
            if (dateFormat.format(flight.getDepartureTime()).equals(keyword)){
                System.out.println("Flight: ");
                System.out.println(flight);
            }
        }
    }
    
    @Override
    public void showFlightsInCurrentDate() {
        Date currentDate = new Date();
        boolean foundFlights = false;

        System.out.println("Flights scheduled for the current date:");

        for (Flight flight : flightManager.values()) {
            String departureDateString = dateFormat.format(flight.getDepartureTime());

            if (departureDateString.equals(dateFormat.format(currentDate))) {
                System.out.println(flight);
                foundFlights = true; 
            }
        }

        if (!foundFlights) {
            System.out.println("No flights found for the current date.");
        }
    }
    
    @Override
    public void saveReservationsDataToFile(){
        reservationManager.saveReservationsToFile();
    }
    
    @Override
    public void saveFlightsDataToFile() throws IOException{
        flightManager.saveFlightsToFile();
    }
    
    @Override
    public void showFlightsWithDepartureDateDescending(){
        for (Flight flight : flightManager.getFlightsByDepartureDateDescending()) {
            System.out.println(flight);
        }
    }
    
    @Override
    public void showAllReservations(){
        reservationManager.showAllReservations();
    }

    @Override
    public void showReservation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateReservation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeReservation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void generateBoardingPass(Reservation reservation) {
        System.out.println("Boarding Pass:");
        System.out.println("Reservation ID: " + reservation.getReservationID());
        System.out.println("Passenger Name: " + reservation.getName());
        System.out.println("Flight Number: " + reservation.getReservationFlightNumber());
        System.out.println("Seat Number: " + reservation.getBoardingPassSeatNumber());
        List<String> boardingPassInformation = new ArrayList<>();
        boardingPassInformation.add(reservation.getReservationID());
        boardingPassInformation.add(reservation.getName());
        boardingPassInformation.add(reservation.getReservationFlightNumber());
        boardingPassInformation.add(reservation.getBoardingPassSeatNumber());
        boardingPassManager.addBoardingPass(reservation.getReservationID(), boardingPassInformation);
    }

    private Flight getSelectedFlight(HashMap<String, Flight> foundFlights) {
        int flightNumber = 1;

        // Display flight choices
        for (Flight flight : foundFlights.values()) {
            System.out.println(flightNumber + ". " + flight.toString());
            flightNumber++;
        }

        while (true) {
            int selectedFlightNumber = validation.getInteger("Select a flight by entering the corresponding number: ", "Invalid selection, please try again", false);

            if (selectedFlightNumber >= 1 && selectedFlightNumber <= foundFlights.size()) {
                Flight[] foundFlightsArray = foundFlights.values().toArray(new Flight[foundFlights.size()]);
                return foundFlightsArray[selectedFlightNumber - 1];
            } else {
                System.out.println("Invalid selection. Please select a valid flight.");
            }
        }
    }  

    private void showSeatsMap(LinkedHashMap<String, Boolean> seats) {

        int maxSeatsPerRow = 4; // Maximum seats per row
        int seatIndex = 0;
        int row = 1;
        System.out.println("=====================================================================");

        for (String seat : seats.keySet()) {
            if (seatIndex == 0) {
                System.out.printf("Row %-4d|", row);
            }

            if (seats.get(seat)) {
                System.out.printf(" %-6s |", seat); 
            } else {
                System.out.printf(" %s(x)  |", seat); // Display an "X" for unavailable seat
            }
            seatIndex++;

            if (seatIndex == 2) {
                System.out.print("                       |"); // Add space between seats
            }

            if (seatIndex >= maxSeatsPerRow) {
                System.out.println();
                seatIndex = 0;
                row++;
            }
        }
        System.out.println("");
        System.out.println("=====================================================================");
    }

}
