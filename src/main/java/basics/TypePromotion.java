package basics;

public class TypePromotion {

    public static void main(String[] args) {
        double a = 10.0;
        Double b = 10.0;
        float c = (float) a;
        promote(a);
        promote(b);
        promote(c);
        promote(10.0);
        promote(10.0f);
    }

    private static void promote(int value) {
        System.out.println("promote int value " + value);
    }
    private static void promoteFloat(float value) {
        System.out.println("promote float value " + value);
    }
    private static void promoteDouble(double value) {
        System.out.println("promote double value " + value);
    }
    private static void promote(Integer value) {
        System.out.println("promote Integer value " + value);
    }
    private static void promote(Float value) {
        System.out.println("promote Float value " + value);
    }
    private static void promote(Double value) {
        System.out.println("promote Double value " + value);
    }
}
