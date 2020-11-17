package lang;

import dto.Car;
import dto.Quad;
import dto.Vehicle;

import java.util.*;

import static jecl.cli.CliPrinter.TypePrinter.withType;
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

        withMemo("Generics::generics1", Generics::generics1);
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

    public static void generics1() {
        Set<? super Number> superNumbers = new HashSet<Number>();
        superNumbers.add(Integer.valueOf(86));
        superNumbers.add(75);
        superNumbers.add(Integer.valueOf(86));
        superNumbers.add(null);
        superNumbers.add(309L);
        Iterator iterator = superNumbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(withType(iterator.next()));
        }
    }
}
