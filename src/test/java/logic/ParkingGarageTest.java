package logic;

import data.Ticket;
import data.spot.ParkingSpot;
import data.spot.SpotType;
import data.vehicle.RegularCar;
import data.vehicle.Truck;
import data.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class ParkingGarageTest {
    private ParkingGarage garage;
    private Vehicle car1;
    private Vehicle car2;
    private Vehicle truck1;


    @BeforeEach
    void setUp() {
        garage = new ParkingGarage();
        garage = new ParkingGarage();
        garage.addSpot(new ParkingSpot(1, SpotType.SMALL));
        garage.addSpot(new ParkingSpot(2, SpotType.SMALL));
        garage.addSpot(new ParkingSpot(3, SpotType.LARGE));
        garage.addSpot(new ParkingSpot(4, SpotType.LARGE));

        car1 = new RegularCar(5, "regular");
        car2 = new RegularCar(6, "regular");
        truck1 = new Truck(7, "truck");

        garage.registerVehicle(car1);
        garage.registerVehicle(car2);
        garage.registerVehicle(truck1);

    }

    @org.junit.jupiter.api.Test
    void exitAndPaySmallCarForAnHour() {

    }

    @Test
    void testReserveSmallSpotForRegularCar() {
        Ticket ticket = garage.parkingVehicle(car1);
        assertNotNull(ticket);
        assertEquals(1, ticket.getSpotId());
        assertTrue(!garage.getAllSpots().stream().filter(s -> s.getId() == 1).findFirst().get().isAvailable());
    }

    @Test
    void testReserveLargeSpotForTruck() {
        Ticket ticket = garage.parkingVehicle(truck1);
        assertNotNull(ticket);
        assertEquals(3, ticket.getSpotId());
        assertTrue(!garage.getAllSpots().stream().filter(s -> s.getId() == 3).findFirst().get().isAvailable());
    }

    @Test
    void testRegularCarUsesLargeSpotWhenSmallFull() {
        // Reserve small spots for car1 and car2
        garage.parkingVehicle(car1);
        garage.parkingVehicle(car2);

        // Now reserve spot for a new regular car; should take large spot L1
        Vehicle car3 = new RegularCar(8, "regular");
        garage.registerVehicle(car3);
        Ticket ticket = garage.parkingVehicle(car3);
        assertNotNull(ticket);
        assertEquals(3, ticket.getSpotId());
    }

    @Test
    void testTruckCannotReserveSmallSpot() {
        // Attempt to reserve a small spot for a truck
        // Since Reservation relies on SpotType's isSuitableFor method, we need to ensure that Truck cannot reserve small spots.
        // Given the current design, Truck's getSuitableSpotTypes() only returns LARGE, so the test would fail if Truck tries to reserve small spots.
        // Instead, ensure that Truck cannot reserve when no large spots are available.

        // Occupy all large spots
        garage.parkingVehicle(truck1);
        Vehicle truck2 = new Truck(22, "truck");
        garage.registerVehicle(truck2);
        garage.parkingVehicle(truck2); // Reserves L2

        // Attempt to reserve another truck; should throw exception
        Vehicle truck3 = new Truck(77, "truck");
        garage.registerVehicle(truck3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> garage.parkingVehicle(truck3));
        assertEquals("No suitable spot found", exception.getMessage());
    }

    @Test
    void testReleaseSpotAndCalculatePayment() {
        Ticket ticket = garage.parkingVehicle(car1);
        LocalDateTime departureTime = ticket.getDate().plusHours(2);
        double payment = garage.releaseSpot(car1.getId(), departureTime);

        assertEquals(19.98, payment, 0.01); // 2 hours * $9.99

        // Ensure the spot is released
        ParkingSpot spot = garage.getAllSpots().stream().filter(s -> s.getId() == (ticket.getSpotId())).findFirst().get();
        assertTrue(spot.isAvailable());
    }



}