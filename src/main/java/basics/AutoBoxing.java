package basics;

public class AutoBoxing {

    public static void main(String[] args) {
        autoBox(false);
        autoBox(10);
        autoBox(10.0);
        autoBox("string");
    }

    private static void autoBox(Object var) {
        System.out.println(var.getClass().getName());
    }
}
