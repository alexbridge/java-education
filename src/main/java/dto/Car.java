package dto;

import java.util.ArrayList;

public class Car extends Vehicle {
    private static Integer wheels = 4;

    private String marke = "VW";
    private transient Integer year;

    ArrayList<Quad> garage;
    //private Object props = new Object(); // java.io.NotSerializableException: java.lang.Object

    static {
        System.out.println("Car class loaded");
    }

    {
        year = 2000;
        garage = new java.util.ArrayList<>(){{
            add(new Quad());
        }};
    }

    public Car() {
        super("Car");
    }

    @Override
    public String toString() {
        return "Car{" +
                "marke='" + marke + '\'' +
                ", year=" + year +
                ", garage=" + garage +
                '}';
    }
}

