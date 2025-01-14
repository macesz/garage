package data;


import data.spot.ParkingSpot;
import data.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final LocalDateTime startTime;
    private final int vehicleId;
    private  final int spotId;
    private final int ticketId;
    private final double pricePerHour;

  public Ticket(LocalDateTime startTime, int vehicleId, int spotId, double pricePerHour) {
      this.startTime = startTime;
      this.vehicleId = vehicleId;
      this.spotId = spotId;
      this.ticketId = UUID.randomUUID().hashCode();
      this.pricePerHour = pricePerHour;

  }

  public LocalDateTime getDate() {
      return startTime;
  }

  public int getSpotId() {
      return spotId;
  }

  public int getVehicleID() {
     return vehicleId;
  }
  public int getTicketID() {
      return ticketId;
  }

 public double calculatePrice(LocalDateTime leavingDateTime) {
      return pricePerHour * getCountOfParkingHours(leavingDateTime);
 }

 private int getCountOfParkingHours(LocalDateTime leavingDateTime) {
      return (int) Duration.between(startTime, leavingDateTime).toHours();
 }

  @Override
    public String toString() {
      return "Ticket" + "Date: " + startTime + ", Vehicle id: " + vehicleId + ", Price per hour: " + pricePerHour;
  }
}
