package basics;

public class Tests {

    static public class Square {
        private double length;

        public Square(double length) {
            this.length = length;
        }
    }

    static public class Cube extends Square {
        public Cube(double length) {
            super(length);
        }
        public double findSurfaceArea() {
            return super.findSurfaceArea() * 6;
        }
    }

    public static void main(String[] args) {
        Square shape = new Cube(1);
        System.out.println(shape.findSurfaceArea());
    }
}