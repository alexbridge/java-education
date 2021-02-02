package lang;

public class Interfaces implements Parrot {

    public static void main(String[] args) {
        Bird b = new Interfaces();
        System.out.println(b.fly());
    }

    @Override
    public String fly() {
        return null;
    }
}

interface Bird {
    default String fly() {
        return "fly";
    }
}

interface Parrot extends Bird {
    public String fly();
}
