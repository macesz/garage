package data.vehicle;

import data.Ticket;
import data.spot.ParkingSpot;
import data.spot.SpotType;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegularCar extends Vehicle {
    public RegularCar(int id, String type) {
        super(id, "regular");
    }

    @Override
    public boolean isSuitable(ParkingSpot spot) {
        return spot.getSpotType() == SpotType.SMALL || spot.getSpotType() == SpotType.LARGE;
    }
}
