package basics;

public class Enums {

    public enum Season {
        WINTER, SPRING, SUMMER, FALL
    }

    public enum SeasonTimes {
        WINTER("9am-5pm"),
        SPRING("9am-5pm"),
        SUMMER("8am-6pm"),
        FALL("9am-5pm");

        private final String hours;

        SeasonTimes(String hours) {
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
            public String getHours() {
                return "10am-3pm";
            }
        };

        public String getHours() { return "9am-5pm"; }
    }

    public enum Planet {
        MERCURY(0.39), VENUS(), EARTH();
        private double distanceFromSunAU = 1.0;
        private Planet(double distance){
            distanceFromSunAU = distance;
        }
        private Planet(){
            distanceFromSunAU = 5.0;
        }
        public double getDistance(){
            return distanceFromSunAU;
        }
    }

    public static void main(String[] args) {
        System.out.println(Season.SUMMER.name() + ":" + Season.SUMMER.ordinal());
        System.out.println(SeasonTimes.SUMMER.name() + ":" + SeasonTimes.SUMMER.getHours());
        System.out.println(SeasonTimesAware.SUMMER.name() + ":" + SeasonTimesAware.SUMMER.getHours());

        for(Planet p : Planet.values()){
            System.out.println(p.getDistance());
        }
    }
}
