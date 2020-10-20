package garage;

public class Test {
    public static void main(String[] args) {
        System.out.printf("\nRunning main method of " + Test.class.getName() + "\n\n");

        DefaultGarage garage = new DefaultGarage();
        garage.getVehicles().stream()
                .forEach(System.out::println);

        System.out.printf("\nDone");
    }
}
