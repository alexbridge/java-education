package section3.lecture13;

public class Encapsulation {

    public static void main(String[] args) {

        String user1 = "Lucy";
        String user2 = "Joey";

        int age1 = 10, age2 = 20;

        Person person1 = new Person(user1, "pass1", age1);
        Person person2 = new Person(user2, "pass2", age2);

        person1.setMoney(110, "pass1");
        person2.setMoney(55, "wrong password");

        System.out.println(person1.getMoney());
        System.out.println(person2.getMoney());
    }
}
