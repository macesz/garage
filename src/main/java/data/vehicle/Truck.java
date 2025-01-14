package data.vehicle;

import data.spot.ParkingSpot;
import data.spot.SpotType;

import java.util.UUID;

public class Truck extends Vehicle {
    public Truck(int id, String type) {
        super(id, "truck");
    }

    @Override
    public boolean isSuitable(ParkingSpot spot) {
        return spot.getSpotType() == SpotType.LARGE;
    }

}
