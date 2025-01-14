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
    private final Map<Integer, ParkingSpot> parkingSpots;
    private final Map<Integer, Vehicle> vehicles;
    private final Map<Integer, Ticket> tickets;


    public ParkingGarage() {
        parkingSpots = new HashMap<>();
        vehicles = new HashMap<>();
        tickets = new HashMap<>();
    }

    public void addSpot(ParkingSpot spot) {
        if (parkingSpots.containsKey(spot.getId())) {
            throw new IllegalArgumentException("Spot ID " + spot.getId() + " already exists.");
        }
        parkingSpots.put(spot.getId(), spot);
    }

    public Collection<ParkingSpot> getParkingSpots() {
        return parkingSpots.values();
    }

    public void registerVehicle(Vehicle vehicle) {
        if (vehicles.containsKey(vehicle.getId())) {
            throw new IllegalArgumentException("Vehicle ID " + vehicle.getId() + " is already registered.");
        }
        vehicles.put(vehicle.getId(), vehicle);
    }


    public Ticket parkingVehicle(Vehicle vehicle) {
        if (!vehicles.containsKey(vehicle.getId())) {
            throw new IllegalArgumentException("Vehicle does not exist");
        }
        if(vehicle.getCurrentParkingSpot() != null) {
            throw new IllegalArgumentException("Vehicle already has a parking spot");
        }

        List<ParkingSpot>  suitableSpot = findSuitableSpots(vehicle);

        if (suitableSpot.isEmpty()) {
            throw new IllegalArgumentException("No suitable spot found");
        }

        ParkingSpot parkingSpot = suitableSpot.getFirst();
        parkingSpot.reserveSpot(vehicle);
        vehicle.setCurrentParkingSpot(parkingSpot);

        double pricePerHour = parkingSpot.getPrice();
        Ticket ticket = new Ticket(LocalDateTime.now(), vehicle.getId(), parkingSpot.getId(), pricePerHour);
        tickets.put(vehicle.getId(), ticket);
        return ticket;
    }


    private List<ParkingSpot> findSuitableSpots(Vehicle vehicle) {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots.values()) {
            if (spot.isAvailable() && vehicle.isSuitable(spot)) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;
    }

    public double releaseSpot(Integer vehicleId, LocalDateTime departureTime) {
        if(!tickets.containsKey(vehicleId)) {
            throw new IllegalArgumentException("Vehicle does not exist");
        }

        Ticket ticket = tickets.get(vehicleId);
        double payment = ticket.calculatePrice(departureTime);

        ParkingSpot parkingSpot = parkingSpots.get(ticket.getSpotId());
        parkingSpot.release();

        Vehicle vehicle = vehicles.get(vehicleId);
        vehicle.setCurrentSpot(null);

        tickets.remove(vehicleId);

        return payment;
    }

    public Collection<ParkingSpot> getAllSpots() {
        return parkingSpots.values();
    }

    public Collection<Vehicle> getAllVehicles() {
        return vehicles.values();
    }

    public Collection<Ticket> getAllTickets() {
        return tickets.values();
    }



//    public List<ParkingSpot> hasAvailableParkingSpotsByType(SpotType spotType) {
//        List<ParkingSpot> availableSpot = new ArrayList<>();
//        for (ParkingSpot parkingSpot : parkingSpots.values()) {
//            if (parkingSpot.getSpotType() == spotType) {
//                availableSpot.add(parkingSpot);
//            }
//        }
//        return availableSpot;
//    }
}
