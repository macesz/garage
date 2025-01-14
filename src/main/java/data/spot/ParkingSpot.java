package data.spot;

import data.Ticket;
import data.vehicle.Vehicle;

import java.time.LocalDateTime;

public class ParkingSpot {
    private int id;
    private boolean isAvailable;
    private SpotType spotType;

    public ParkingSpot(int id, SpotType spotType) {
        this.id = id;
        this.isAvailable = true;
        this.spotType = spotType;
    }

    public int getId() {
        return id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return spotType.getPrice();
    }

    public void reserveSpot(Vehicle vehicle) {
        if (!isAvailable) {
            throw new IllegalStateException("Spot is not available");
        }
        isAvailable = false;
        Ticket ticket = new Ticket(LocalDateTime.now(), vehicle.getId(), this.id, spotType.getPrice());
    }

    public void release() {
        isAvailable = true; // Mark the spot as unoccupied
    }

}
