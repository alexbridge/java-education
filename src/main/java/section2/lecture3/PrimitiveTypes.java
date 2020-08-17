package section2.lecture3;

public class PrimitiveTypes {

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
    }
}
