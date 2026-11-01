package util.concurrent;

public class Volatiles {

    int intA;
    volatile int intB;

    public static void main(String[] args) {
        Volatiles volatiles = new Volatiles();

        new Thread(() -> {
            while (volatiles.intB < 2) {
                System.out.println("1: intB is still less then 2");
            }
            System.out.println("1: " + volatiles.intA);
            System.out.println("1: " + volatiles.intB);
        }).start();

        new Thread(() -> {
            System.out.println("1: " + volatiles.intA);
            System.out.println("1: " + volatiles.intB);
            volatiles.intB = 2;
        }).start();
    }
}
