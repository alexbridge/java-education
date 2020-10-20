package basics;

public class Enums {

    public enum Season {
        WINTER, SPRING, SUMMER, FALL
    }

    public enum SeasonTimesAware {
        WINTER {
            public String getHours() {
                return "10am-3pm";
            }
        },
        SPRING,
        SUMMER,
        FALL {
            public String getHours() {
                return "10am-3pm";
            }
        };

        public String getHours() { return "9am-5pm"; }
    }

    public static void main(String[] args) {
        System.out.println(Season.SUMMER.name() + ":" + Season.SUMMER.ordinal());
        System.out.println(SeasonTimesAware.SUMMER.name() + ":" + SeasonTimesAware.SUMMER.getHours());
    }
}
