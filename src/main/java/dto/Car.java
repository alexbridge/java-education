package dto;

public class Car extends Vehicle {
    static {
        System.out.println("Car class loaded");
    }
    public Car() {
        super("Car");
    }
}

