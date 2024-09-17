
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileDaoImpl implements Serializable{

    private File file;

    public FileDaoImpl(String filename) {
        this.file = new File(filename);
    }

    public void saveFlightData(FlightDaoImpl object) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(object);
            System.out.println("Data saved to " + file.getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public FlightDaoImpl loadFlightData() throws IOException, ClassNotFoundException {
        FlightDaoImpl result = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (FlightDaoImpl) ois.readObject();
            System.out.println("Data loaded from " + file.getName());
        }
        return result;
    }
    public void saveReservationData(ReservationDaoImpl object) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(object);
            System.out.println("Data saved to " + file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ReservationDaoImpl loadReservationData() throws IOException, ClassNotFoundException {
        ReservationDaoImpl result = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (ReservationDaoImpl) ois.readObject();
            System.out.println("Data loaded from " + file);
        }
        return result;
    }
}
