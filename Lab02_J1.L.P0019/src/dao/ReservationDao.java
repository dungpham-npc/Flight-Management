package dao;

import java.util.HashMap;
import model.Reservation;


public interface ReservationDao {

    void addReservation(String reservationID, Reservation reservation);

    Reservation getReservation(String reservationID);

    HashMap<String, Reservation> getAllReservations();

    void updateReservation(String reservationID, Reservation updatedReservation);

    void removeReservation(String reservationID);
    
    void showAllReservations();
}
