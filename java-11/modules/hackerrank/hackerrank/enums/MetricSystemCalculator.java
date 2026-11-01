package hackerrank.enums;

import java.text.DecimalFormat;

public class MetricSystemCalculator {

    public static void main(String[] args) {

        calculate(1, "metre", "metre");
        calculate(1, "metre", "inch");
        calculate(1, "metre", "foot");

        calculate(8, "bit", "byte");
    }

    private static void calculate(int value, String from, String to) {

        Class[] enums = {
                LengthEnum.class,
                BitDataEnum.class
                /* Add MOre Enum libraries here */
        };

        try {
            for (Class clazz : enums) {
                try {
                    // 1. Try to find Enum, which suports input
                    EnumGetNameValue enumFrom = (EnumGetNameValue) Enum.valueOf(clazz, from.toUpperCase());

                    EnumGetNameValue enumTo;
                    try {
                        // 2. Check if same Enum supports output
                        enumTo = (EnumGetNameValue) Enum.valueOf(clazz, to.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Output is expected same type aas input");
                    }

                    // 4. Apply calculation and output
                    double result =  value * enumFrom.getValue() * enumTo.getValue();
                    System.out.println(value + from + " is: " + new DecimalFormat("#.#####").format(result) + to);

                } catch (IllegalArgumentException e) {
                    // throw new RuntimeException(e.getMessage());
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    interface EnumGetNameValue {
        String getName();
        double getValue();
    }

    enum LengthEnum implements EnumGetNameValue {

        METRE ("metre", 1),
        INCH ("inch", 39.3701),
        FOOT ("foot", 3.2808);

        private final String name;
        private final double value;

        LengthEnum(String name, double value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getValue() {
            return value;
        }
    }

    enum BitDataEnum implements EnumGetNameValue {

        BIT ("bit", 1),
        BYTE ("byte", 8);

        private final String name;
        private final double value;

        BitDataEnum(String name, double value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getValue() {
            return value;
        }
    }
}
