package logic;

import data.spot.ParkingSpot;
import data.spot.SpotType;
import data.vehicle.RegularCar;
import data.vehicle.Truck;
import data.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class ParkingGarageTest {
    private ParkingGarage parkingGarage;
    private RegularCar regularCar;
    private ParkingSpot parkingSpot;


    @BeforeEach
    void setUp() {
        parkingGarage = new ParkingGarage();


    }

    @org.junit.jupiter.api.Test
    void exitAndPaySmallCarForAnHour() {
        parkingSpot = new ParkingSpot(1, SpotType.SMALL);
        parkingGarage.addParkingSpot(parkingSpot);

        regularCar = new RegularCar(1, "regular");
        parkingGarage.parkVehicle(regularCar);


        LocalDateTime start = LocalDateTime.now().minusHours(1);
        LocalDateTime end = LocalDateTime.now();

        double expectedFee = 1 * parkingSpot.getSpotType().getPrice();
        double actualGee = parkingGarage.exitGarage(regularCar);

        assertEquals(expectedFee, actualGee, 0.001);

    }
}