package basics;

public class AutoBoxing {

    public static void main(String[] args) {
        autoBox(false);
        autoBox((byte) 10);
        autoBox((short) 10);
        autoBox((char) 10);
        autoBox(10);
        autoBox(10L);
        autoBox(10.0f);
        autoBox(10.0);
        autoBox("string");
    }

    private static void autoBox(Object var) {
        System.out.println(var.getClass().getName());
    }
}
