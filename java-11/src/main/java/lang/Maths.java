package lang;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Maths {

    public static void main(String[] args) {

        BigDecimal bigDecimal = BigDecimal.valueOf(10.4568);

        System.out.println(bigDecimal);
        System.out.println(BigDecimal.ZERO);
        System.out.println(BigDecimal.ONE);
        System.out.println(BigDecimal.TEN);

        BigDecimal scaled = bigDecimal
                .multiply(BigDecimal.valueOf(3))
                .setScale(4, RoundingMode.HALF_UP);
        System.out.println(scaled);
    }
}
