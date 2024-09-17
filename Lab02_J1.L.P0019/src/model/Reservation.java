
package model;

import java.io.Serializable;

/**
 *
 * @author canhd
 */
public class Reservation implements Serializable{
    private static int reservationIDCount = 0;
    private String reservationID;
    private String name;
    private String address;
    private String phoneNumber;
    private String identityCardNumber;
    private String reservationFlightNumber;
    private String boardingPassSeatNumber;
    

    public Reservation(String name, String address, String phoneNumber, String identityCardNumber, String reservationFlightNumber) {
        this.reservationID = generateReservationCode();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.identityCardNumber = identityCardNumber;
        this.reservationFlightNumber = reservationFlightNumber;
    }

    public Reservation(){}

    public static int getReservationIDCount() {
        return reservationIDCount;
    }

    public static void setReservationIDCount(int reservationIDCount) {
        Reservation.reservationIDCount = reservationIDCount;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getReservationFlightNumber() {
        return reservationFlightNumber;
    }

    public void getReservationFlightNumber(String boardingPassFlightNumber) {
        this.reservationFlightNumber = boardingPassFlightNumber;
    }

    public String getBoardingPassSeatNumber() {
        return boardingPassSeatNumber;
    }

    public void setBoardingPassSeatNumber(String boardingPassSeatNumber) {
        this.boardingPassSeatNumber = boardingPassSeatNumber;
    }
    
    private String generateReservationCode() {
        int IDCount = ++reservationIDCount;
        String codeStr = String.format("%s%07d", "R", IDCount);
        return codeStr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+---------------------------+-----------------------------------+\n");
        sb.append("|             Field         |              Value                |\n");
        sb.append("+---------------------------+-----------------------------------+\n");
        sb.append(String.format("| Reservation ID            | %-27s       |\n", reservationID));
        sb.append(String.format("| Name                      | %-27s       |\n", name));
        sb.append(String.format("| Address                   | %-27s       |\n", address));
        sb.append(String.format("| Phone Number              | %-27s       |\n", phoneNumber));
        sb.append(String.format("| ID Card Number            | %-27s       |\n", identityCardNumber));
        sb.append(String.format("| Reservation Flight Number | %-27s       |\n", reservationFlightNumber));
        sb.append(String.format("| Boarding Pass Seat Number | %-27s       |\n", boardingPassSeatNumber));
        sb.append("+---------------------------+-----------------------------------+\n");
        return sb.toString();
    }

    
}
