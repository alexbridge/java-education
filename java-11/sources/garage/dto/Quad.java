package garage.dto;

public class Quad extends Vehicle {
    static {
        System.out.println("Quad class loaded");
    }
    public Quad() {
        super("Quad");
    }
}

