package dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import model.Flight;


public class FlightDaoImpl extends HashMap<String, Flight> implements FlightDao, Serializable {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    FileDaoImpl fileManager = new FileDaoImpl("flight.dat");
    public FlightDaoImpl() {
        loadFlightsFromFile();
    }

    private void loadFlightsFromFile() {
        try {
            Serializable data = fileManager.loadFlightData();
            if (data instanceof HashMap) {
                HashMap<String, Flight> flightsFromFile = (HashMap<String, Flight>) data;
                this.putAll(flightsFromFile);
                System.out.println("Data loaded from flight.dat");
            } else {
                System.out.println("Invalid data found in flight.dat");
            }
        } catch (Exception e) {
            System.out.println("Error loading data from flight.dat: " + e.getMessage());
        }
    }

    @Override
    public void addFlight(String flightNumber, Flight flight) {
        this.put(flightNumber, flight);
    }

    @Override
    public Flight getFlight(String flightNumber) {
        return this.get(flightNumber);
    }

    @Override
    public void updateFlight(String flightNumber, Flight updatedFlight) {
        if (this.containsKey(flightNumber)) {
            this.put(flightNumber, updatedFlight);
        }
    }

    @Override
    public void removeFlight(String flightNumber) {
        this.remove(flightNumber);
    }
    
    @Override
    public void showAllFlights(){
        for (String i : this.keySet()) {
            System.out.println("FlightID: " + i + " \nvalue:\n" + this.get(i));
        }
    }
    
    public HashMap<String, Flight> getFlightsByKeyword(String keyword){
        HashMap<String, Flight> foundFlights = new HashMap<>();
        for (Flight flight : this.values()) {
            if (flight.getDepartureCity().toLowerCase().contains(keyword.toLowerCase()) 
                    || flight.getDestinationCity().toLowerCase().contains(keyword.toLowerCase()) 
                    || dateFormat.format(flight.getDepartureTime()).toLowerCase().contains(keyword.toLowerCase()) 
                    || dateFormat.format(flight.getArrivalTime()).toLowerCase().contains(keyword.toLowerCase()))
                foundFlights.put(flight.getFlightNumber(), flight);
        }
        return foundFlights;
    }
    
    public List<Flight> getFlightsByDepartureDateDescending() {
        List<Flight> flightList = new ArrayList<>(this.values());

        // Define a custom comparator for sorting flights by departure date in descending order
        Comparator<Flight> departureDateComparator = new Comparator<Flight>() {
            @Override
            public int compare(Flight flight1, Flight flight2) {
                return flight2.getDepartureTime().compareTo(flight1.getDepartureTime());
            }
        };

        // Sort the flight list using the custom comparator
        Collections.sort(flightList, departureDateComparator);

        return flightList;
    }
    
    public void saveFlightsToFile() {
        try {
            fileManager.saveFlightData(this);
        } catch (Exception e) {
            System.out.println("Error saving flights data to flight.dat: " + e.getMessage());
        }
    }
  
}
