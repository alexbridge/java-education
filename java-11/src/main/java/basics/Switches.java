package basics;

public class Switches {

    public static void main(String[] args) {
        // Compile constant
        final int sunday = 0;

        for (var day : new int[]{1,0, 2}) {
            switch (day) {
                case 1:
                    System.out.println("I am Monday");
                    break;
                case 2:
                    System.out.println("I am Tuesday");
                    break;
                case sunday:
                    System.out.println("I am Sunday");
            }
        }
    }
}