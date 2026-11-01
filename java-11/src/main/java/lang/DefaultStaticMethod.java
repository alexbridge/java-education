package lang;

public class DefaultStaticMethod {

    interface Black {
        default String getColor() {
            return Black.class.getName();
        }
    }

    interface Red {
        default String getColor() {
            return Red.class.getName();
        }
    }

    interface Gold {
        private String getColor() {
            return Gold.class.getName();
        }
    }

    static class DefaultStaticMethodTest implements Black, Red, Gold {
        @Override
        public String getColor() {
            return Gold.super.getColor();
        }
    }

    public static void main(String[] args) {
        var b = new DefaultStaticMethodTest();
        System.out.println(b.getColor());
    }
}

