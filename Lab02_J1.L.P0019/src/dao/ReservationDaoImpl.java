package dao;


import java.io.Serializable;
import java.util.HashMap;
import model.Reservation;

/**
 *
 * @author canhd
 */
public class ReservationDaoImpl extends HashMap<String, Reservation> implements ReservationDao, Serializable {
    FileDaoImpl fileManager = new FileDaoImpl("reservation.dat");
    public ReservationDaoImpl() {
        loadFlightsFromFile();
    }

    private void loadFlightsFromFile() {
        try {
            Serializable data = fileManager.loadReservationData();
            if (data instanceof HashMap) {
                HashMap<String, Reservation> reservationsFromFile = (HashMap<String, Reservation>) data;
                this.putAll(reservationsFromFile);
                updateReservationIDCount(this);
                System.out.println("Data loaded from reservation.dat");
            } else {
                System.out.println("Invalid data found in reservation.dat");
            }
        } catch (Exception e) {
            System.out.println("Error loading data from reservation.dat: " + e.getMessage());
        }
    }

    // Method to update reservationIDCount based on loaded reservations
    private void updateReservationIDCount(HashMap<String, Reservation> loadedReservations) {
        int maxID = 0;
        for (Reservation reservation : loadedReservations.values()) {
            String reservationID = reservation.getReservationID();
            if (reservationID != null && reservationID.matches("^R\\d{7}$")) {
                int idNumber = Integer.parseInt(reservationID.substring(1));
                if (idNumber > maxID) {
                    maxID = idNumber;
                }
            }
        }
        // Update reservationIDCount to be greater than the maximum loaded ID
        Reservation.setReservationIDCount(maxID);
    }
    
    @Override
    public void addReservation(String reservationID, Reservation reservation) {
        this.put(reservationID, reservation);
    }

    @Override
    public Reservation getReservation(String reservationID) {
        return this.get(reservationID);
    }

    @Override
    public HashMap<String, Reservation> getAllReservations() {
        return new HashMap<>(this);
    }

    @Override
    public void updateReservation(String reservationID, Reservation updatedReservation) {
        this.put(reservationID, updatedReservation);
    }

    @Override
    public void removeReservation(String reservationID) {
        this.remove(reservationID);
    }

    @Override
    public void showAllReservations() {
        for (String i : this.keySet()) {
            System.out.println("ReservationID: " + i + " \nvalue:\n" + this.get(i));
        }
    }

    public HashMap<String, Reservation> getReservationsForFlight(String flightNumber) {
        HashMap<String, Reservation> flightReservations = new HashMap<>();
        for (Reservation reservation : this.values()) {
            if (reservation.getReservationFlightNumber().equals(flightNumber)) {
                flightReservations.put(reservation.getReservationID(), reservation);
            }
        }
        return flightReservations;
    }
    
    public void saveReservationsToFile() {
        try {
            fileManager.saveReservationData(this);
        } catch (Exception e) {
            System.out.println("Error saving flights data to reservation.dat: " + e.getMessage());
        }
    }

}
