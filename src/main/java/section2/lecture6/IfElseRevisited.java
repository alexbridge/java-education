package section2.lecture6;

public class IfElseRevisited {

    public static void main(String[] args) {

        if (4 == 3) { // 3 ==3 --> is 3 equal to 3 ?
            System.out.println("We are inside our if statement");
        } else {
            System.out.println("We are inside of else statement");
        }

        double defaultTicket = 10.0;
        double discount = 0.1;
        double ticketPrice = defaultTicket;
        int age = 19;
        if (age < 18) {
            // The give customer a discount of 10%
            ticketPrice = ticketPrice - ticketPrice * discount;
        } else if (age < 25) { // has to be greater then 18 at this point
            // Give the customer a discount of 5%
            ticketPrice = ticketPrice - ticketPrice * .05;
        }

        System.out.println(ticketPrice);
    }
}
