package section2.lecture4;

public class MoreDataTypes {

    public static void main(String[] args) {

        int myInt = 20; // 30,4,5,3,-100
        double myDouble = 20.25; //30.1, 50.222
        int sum = (int) (myDouble + myInt); // 40.25 --> 40

        boolean isRainy = true; // 0 = false; 1 = true
        if (isRainy) {
            System.out.println("You need an umbrella");
        }

        int nine = 9;
        char myChar = '9'; // A --> number 57 ---> 00001111 ---> number 57 --> A
        // 9 --> number 37 --> 0001111 (not the actually binary sequence)
        // number 9 --> 00001001

        int mappedVal = (int) myChar;
        System.out.println(mappedVal);

        // 'A' + 'B' + 'C';

        int a = 2;
        int b = 3;
        int c = a / b; // 2 / 3 = 0.6666 => 0
        int d = b / a; // 3 / 2 = 1.5 => 1
        System.out.println(d);

        int f = 20;
        double r = 22;
        int result = (int) (f / r); // 20 / 22 => 0.909 --> 0
        double result2 = f / r; // 20 / 22 => 0.909 --> 0.909

        System.out.println(result);
        System.out.println(result2);
    }
}
