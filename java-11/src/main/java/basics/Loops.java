package basics;

public class Loops {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(++i);
        }

        for (int i = 0; i < 5; ) {
            System.out.println(++i);
            i++;
        }
    }
}