package lang;

public class Arrays {

    public static void main(String[] args) {
        int size = 2;
        int[] ints = new int[size];
        System.out.println(ints[0]);

        String[] strs = new String[size];
        System.out.println(strs[0]);

        int[] array = { 6, -4, 12, 0, -10 };
        // !! Must be sorted
        java.util.Arrays.sort(array);
        System.out.println(java.util.Arrays.binarySearch(array, 0));
    }
}
