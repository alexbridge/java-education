package section2.lecture7;

public class SwitchStatement {

    public static void main(String[] args) {

        double defaultTicket = 10.0;
        double discount = 0.1;
        double ticketPrice = defaultTicket;
        int age = 30;

        if (age < 18) {
            // The give customer a discount of 10%
            ticketPrice = ticketPrice - ticketPrice * discount;
        } else if (age < 25) { // has to be greater then 18 at this point
            // Give the customer a discount of 5%
            ticketPrice = ticketPrice - ticketPrice * .05;
        }
        //System.out.println(ticketPrice);

        if (age == 30 || age == 50) {
            //System.out.println("You are now an old man");
        } else if (age == 20) {
            //System.out.println("You are also and old man");
        } else if (age == 10) {
            //System.out.println("You are now a child");
        }

        switch (age) {
            case 4:
                System.out.println("We are now inside case 4");
                break;
            case 3:
                System.out.println("We are now inside case 3");
                break;
            case 30:
            case 50:
                System.out.println("You are now an old man");
                break;
        }
    }
}
