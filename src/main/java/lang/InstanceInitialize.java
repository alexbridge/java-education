package lang;

import dto.Car;
import dto.Vehicle;

public class InstanceInitialize {
    static int integer1; // static, defaults to 0
    int integer2; // instance, defaults to 0

    public static void main(String[] args) {
        int integer3 = 0;  // local, must be initialized

        System.out.println(Vehicle.type);
        System.out.println(Car.type);
        System.out.println(integer1);
        System.out.println(integer3);
        System.out.println(new InstanceInitialize().integer2 );
    }
}
