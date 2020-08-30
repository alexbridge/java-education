package lang;

import dto.Car;
import dto.Quad;
import dto.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static jecl.cli.CliPrinter.withMemo;

public class Generics {

    public static void main(String[] args) {
        withMemo("Generics::generic", () -> {
            List<String> els = returnList(new ArrayList<>() {{
                add("One");
            }});
            System.out.println(els);

            String firstEl = returnFirstEl(new ArrayList<>() {{
                add("One");
            }});
            System.out.println(firstEl);
        });

        withMemo("Generics::wildcard", () -> {
            List<? extends Vehicle> extendVehicles = returnFirstLowerVehicle(new ArrayList<>() {{
                add(new Quad());
                add(new Car());
                add(new Vehicle());
            }});
            System.out.println(extendVehicles.get(0));
        });
    }

    public static <E> List<E> returnList(List<E> list) {
        return list;
    }

    public static <E> E returnFirstEl(List<E> list) {
        return list.get(0);
    }

    public static List<? extends Vehicle> returnFirstLowerVehicle(List<? extends Vehicle> vehicles) {
        return vehicles;
    }
}
