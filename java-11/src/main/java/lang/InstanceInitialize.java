package lang;

import dto.Car;
import dto.Vehicle;
import jecl.cli.CliPrinter;

import java.util.HashMap;
import java.util.Map;

public class InstanceInitialize {
    static int integer1; // static, defaults to 0
    int integer2; // instance, defaults to 0
    char instChar; // instance, defaults to \0000 -> ASCI null
    long someLong;
    float someFloat;
    double someDouble;

    private Map<String, String> days = new HashMap<String, String>(){{
        put("1", "Monday");
    }};
    {
        days.put("2", "Tuesday");
    }

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
        System.out.println(new InstanceInitialize().days);

        InstanceInitialize inst = new InstanceInitialize();
        inst.someLong = 200L;
        inst.someDouble = 200.0;
        inst.someFloat = 200.0f; // 200.0 is double and not fit into float
        System.out.println(CliPrinter.TypePrinter.withType(inst.someLong));
        System.out.println(CliPrinter.TypePrinter.withType(inst.someDouble));
        System.out.println(CliPrinter.TypePrinter.withType(inst.someFloat));

        long otherLong = 100L;
        float otherFloat = 1_00.0f;
        System.out.println(CliPrinter.TypePrinter.withType(otherLong));
        System.out.println(CliPrinter.TypePrinter.withType(otherFloat));
    }
}
