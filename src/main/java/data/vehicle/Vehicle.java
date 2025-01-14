package data.vehicle;

import data.Ticket;
import data.spot.ParkingSpot;

import java.time.LocalDateTime;

public abstract class Vehicle {
    private int id;
    private String type;
    private Ticket ticket;

    public Vehicle(int id, String type) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isParked(){
        return ticket != null;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public boolean reserveParkingSpot(ParkingSpot parkingSpot){
        double price = parkingSpot.getSpotType().getPrice();
        ticket = new Ticket(LocalDateTime.now(),this.getId(), parkingSpot.getId(), price);
        return true;
    }

    public double getParkingFee(LocalDateTime time){
        return ticket.calculatePrice(time);
    }

}
