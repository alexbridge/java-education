package basics;

import java.text.MessageFormat;

public class Enums {

    public enum Season {
        WINTER, SPRING, SUMMER, FALL
    }

    public enum SeasonTimes {
        WINTER,
        SPRING,
        SUMMER("8am-6pm"),
        FALL;

        private final String hours;
        private SeasonTimes() {
            this.hours = "9am-5pm";
        }
        private SeasonTimes(String hours) {
            this.hours = hours;
        }

        public String getHours() {
            return hours;
        }
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
            @Override
            public boolean isHealthy() {
                return false;
            }

            public String getHours() {
                return "10am-3pm";
            }
        };

        public boolean isHealthy() {
            return true;
        };

        public String getHours() { return "9am-5pm"; }
    }

    public static void main(String[] args) {
        System.out.println(Season.SUMMER.name() + ":" + Season.SUMMER.ordinal());
        System.out.println(SeasonTimes.SUMMER.name() + ":" + SeasonTimes.SUMMER.getHours());
        System.out.println(SeasonTimesAware.SUMMER.name() + ":" + SeasonTimesAware.SUMMER.getHours());

        String message = "{0}: {1}: {2}";

        for(SeasonTimes p : SeasonTimes.values()) {
            System.out.println(
                    MessageFormat.format(
                            message,
                            p.ordinal(),
                            p.name(),
                            p.getHours()
                    )
            );
        }

        Season SUMMER = Season.FALL;
        switch (SUMMER) {
            case WINTER: System.out.print("v");
            case FALL: System.out.print("v");
            case SPRING: System.out.print("c");
            case SUMMER: System.out.print("s");
            break;
            default: System.out.println("missing season"); }
    }
}
