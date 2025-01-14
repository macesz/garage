package data.vehicle;

import data.Ticket;
import data.spot.ParkingSpot;
import logic.ParkingGarage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    private int id;
    private String type;
    private ParkingSpot currentParkingSpot;

    public Vehicle(int id, String type) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public  void  setCurrentSpot(ParkingSpot currentParkingSpot) {
        this.currentParkingSpot = currentParkingSpot;
    }
    public ParkingSpot getCurrentParkingSpot() {
        return currentParkingSpot;
    }
    public void setCurrentParkingSpot(ParkingSpot currentParkingSpot) {
        this.currentParkingSpot = currentParkingSpot;
    }

    public List<ParkingSpot> findSuitableSpots(ParkingGarage garage) {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : garage.getParkingSpots()) {
            if (spot.isAvailable() && isSuitable(spot)) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;
    }

    public abstract boolean isSuitable(ParkingSpot spot);


}
