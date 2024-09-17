
package controller;

import java.io.IOException;



/**
 *
 * @author canhd
 */
public interface IController {
    void addFlight();
    
    void getFlight();
    
    void updateFlight();
    
    void removeFlight();
    
    void showAllFlights();
    
    void addReservation();
    
    void showReservation();
    
    void updateReservation();
    
    void removeReservation();
    
    void showAllReservations();
    
    void flightCheckIn();
    
    void selectSeat();
    
    void assignCrewToFlight();
    
    void showFlightsInSpecificDepartureDate();

    void showFlightsInCurrentDate();
    
    void saveReservationsDataToFile();
    
    void saveFlightsDataToFile() throws IOException;
    
    void showFlightsWithDepartureDateDescending();
    
}
