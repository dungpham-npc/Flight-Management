
package dao;

import model.Flight;

/**
 *
 * @author canhd
 */
public interface FlightDao {
    void addFlight(String flightNumber, Flight flight);
    
    Flight getFlight(String flightNumber);
    
    void updateFlight(String flightNumber, Flight updatedFlight);
    
    void removeFlight(String flightNumber);
    
    void showAllFlights();
}
