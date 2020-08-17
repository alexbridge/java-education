package section3.lecture11;

public class Functions {

    public static void main(String[] args) {

        int x = 3;
        int y = 3;
        int add = x + y;
        int add2 = add(x, y);

    }

    static int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    // 7 is a prime number. 13, 17, 19, 23
    // 7 % 2 = 1, Remainder is 1
    // 7 % 3 = 1, Remainder is 1
    // 7 % 4 = 1, Remainder is 1
    // 7 % 7 = 0, 7 % 1 = 0
    // 8 % 2 = 0, 8 is not prime becaise we got remonder 0 for a number other then 1 and 8
    static boolean isNumberPrime(int a) {

        for (int i = 2; i < a; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }
}
