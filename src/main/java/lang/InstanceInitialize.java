package lang;

import dto.Car;
import dto.Vehicle;

public class InstanceInitialize {
    static int integer1; // static, defaults to 0
    int integer2; // instance, defaults to 0
    char instChar; // instance, defaults to \0000 -> ASCI null

    public static void main(String[] args) {
        int integer3 = 0;  // local, must be initialized to be used
        int integer4; // can be defined without usage

        new Vehicle();
        new Car();

        System.out.println(integer1);
        System.out.println(integer3);
        //System.out.println(integer4);
        System.out.println(new InstanceInitialize().integer2);
        System.out.println(new InstanceInitialize().instChar == Character.MIN_VALUE);
    }
}
