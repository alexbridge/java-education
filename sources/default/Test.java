import java.util.Arrays;

/**
 * CLI example:
 * - java Test.java
 * - javac Test.java
 * - jar cf test.jar Test.class
 * - java -cp test.jar Test
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Default class in default module");

        System.out.println(getCumulativeValue());
        System.out.println(getCumulativeValue(1,2,3));
    }

    public static int getCumulativeValue(int... values) {
        System.out.println(values);
        System.out.println(Arrays.toString(values));
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }
}
