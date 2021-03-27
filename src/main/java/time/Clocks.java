package time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Clocks {

    public static void main(String[] args) {
        Clock sys = Clock.systemDefaultZone();
        Clock start = Clock.fixed(
                Instant.now(),
                ZoneId.systemDefault()
        );
        String startString = start.toString();
        System.out.println("sys " + LocalDateTime.now(sys));
        System.out.println(LocalDateTime.now());
        System.out.println(Instant.now());
        System.out.println("fixed " + start);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sys " + LocalDateTime.now(sys));
        System.out.println(LocalDateTime.now());
        System.out.println(Instant.now());
        System.out.println("fixed " + start);
    }
}
