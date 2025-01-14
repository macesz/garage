package data.spot;

import java.util.Scanner;
import java.util.UUID;

public class ParkingSpot {
    private int id;
    private boolean available;
    private SpotType spotType;

    public ParkingSpot(int id, SpotType spotType) {
        this.id = id;
        this.available = true;
        this.spotType = spotType;
    }

    public int getId() {
        return id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void occupy() {
        available = false;
    }

    public void free() {
        available = true;
    }


}
