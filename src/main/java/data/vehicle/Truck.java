package data.vehicle;

import data.spot.ParkingSpot;
import data.spot.SpotType;

import java.util.UUID;

public class Truck extends Vehicle {
    public Truck(int id, String type) {
        super(id, "truck");
    }

    @Override
    public boolean reserveParkingSpot(ParkingSpot parkingSpot) {
        if (parkingSpot.isAvailable() && parkingSpot.getSpotType() == SpotType.LARGE) {
            return super.reserveParkingSpot(parkingSpot);
        }
        return false;
    }
}
