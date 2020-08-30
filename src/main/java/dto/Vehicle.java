package dto;

import java.io.Serializable;

public class Vehicle implements Serializable {
    static {
        System.out.println("Vehicle class loaded");
    }
    public String type;

    public Vehicle() {
        this("Vehicle");
    }

    public Vehicle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

