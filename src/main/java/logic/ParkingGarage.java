package logic;

import data.spot.ParkingSpot;
import data.Ticket;
import data.spot.SpotType;
import data.vehicle.RegularCar;
import data.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ParkingGarage {
    private final Set<ParkingSpot> parkingSpots;

    public ParkingGarage() {
        parkingSpots = new HashSet<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }

    public Set<ParkingSpot> getAvaliableParkingSpots() {
        return parkingSpots.stream()
                .filter(ParkingSpot::isAvailable)
                .collect(Collectors.toSet());
    }

    public boolean hasAvaliableParkingSpotsByType(SpotType spotType) {
        return parkingSpots.stream()
                .filter(ParkingSpot::isAvailable)
                .anyMatch(parkingSpot -> parkingSpot.getSpotType() == spotType);
    }

    public ParkingSpot getSpotById(int id) {
        return parkingSpots.stream().filter(parkingSpot -> parkingSpot.getId() == id).toList().getFirst();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (vehicle.isParked()) {
            return false;
        }

        // Get available parking spots
        Set<ParkingSpot> availableSpots = getAvaliableParkingSpots();

        if (availableSpots.isEmpty()) {
            return false;

        }


        SpotType spotTypeToCheck = vehicle.getType().equals("regular") ? SpotType.SMALL : SpotType.LARGE;

        for (ParkingSpot parkingSpot : availableSpots) {
            if (parkingSpot.getSpotType() == spotTypeToCheck) {
                return vehicle.reserveParkingSpot(parkingSpot);
            }
        }
        return false;
    }

    public double exitGarage(Vehicle vehicle) {
        double parkingFee = vehicle.getParkingFee(LocalDateTime.now());
        ParkingSpot parkingSpot = getSpotById(vehicle.getId());
        parkingSpot.free();
        vehicle.setTicket(null);
        return parkingFee;
    }


}
