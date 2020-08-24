package lang;

public class Loops {

    public static void main(String[] args) {
        forWithIdentifier();
    }

    public static void forWithIdentifier() {
        outerLoop:
        for (int i = 1; i < 1000; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    System.out.println("Continue on " + i);
                    continue outerLoop;
                }

                if (i % 5 == 0) {
                    System.out.println("Break on " + i);
                    break outerLoop;
                }
            }
            System.out.println("In loop " + i);
        }
    }
}
