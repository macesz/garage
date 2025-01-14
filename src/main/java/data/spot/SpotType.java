package data.spot;

import data.vehicle.Vehicle;

public enum SpotType {
    SMALL("small", 9.99, "s"),
    LARGE("large", 19.99, "b");

    private final String type;
    private final double price;
    private final String carType;

    private SpotType(String type, double price, String carType) {
        this.type = type;
        this.price = price;
        this.carType = carType;
    }
    public String getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public String getCarType() {
        return carType;
    }



}
