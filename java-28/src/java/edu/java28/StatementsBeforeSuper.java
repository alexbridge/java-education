package edu.java28;

/**
 * Statements before {@code super(...)} (Flexible Constructor Bodies) - [final].
 * <p>
 * Feature focus:
 * - Allows statements before explicit constructor calls ({@code super(...)} or {@code this(...)}).
 * - Enables argument validation, argument preparation, and pre-processing in constructor prologue.
 */
public class StatementsBeforeSuper {

    static class Vehicle {
        private final String vin;

        public Vehicle(String vin) {
            this.vin = vin;
        }

        public String getVin() {
            return vin;
        }
    }

    static class ElectricCar extends Vehicle {
        private final int batteryKwh;

        public ElectricCar(String vin, int batteryKwh) {
            // Flexible Constructor Bodies: statements allowed before super()
            // Validate arguments or preprocess before invoking superclass constructor
            if (vin == null || vin.isBlank()) {
                throw new IllegalArgumentException("VIN cannot be blank");
            }
            var normalizedVin = vin.trim().toUpperCase();

            super(normalizedVin);

            if (batteryKwh <= 0) {
                throw new IllegalArgumentException("Battery capacity must be positive");
            }
            this.batteryKwh = batteryKwh;
        }

        public int getBatteryKwh() {
            return batteryKwh;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Flexible Constructor Bodies Demo ===");

        var car = new ElectricCar("   vin-12345-ev   ", 75);
        System.out.println("Normalized VIN: " + car.getVin());
        System.out.println("Battery: " + car.getBatteryKwh() + " kWh");

        try {
            new ElectricCar("", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Pre-super validation caught invalid input: " + e.getMessage());
        }
    }
}
