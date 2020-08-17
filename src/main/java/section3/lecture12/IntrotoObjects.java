package section3.lecture12;

public class IntrotoObjects {

    public static void main(String[] args) {

        String user1 = "Lucy";
        String user2 = "Joey";
        String user3 = "Willy";

        int age1 = 10, age2 = 20, age3 = 30;

        Person person1 = new Person(user1, age1);
        Person person2 = new Person(user2, age2);
        Person person3 = new Person(user3, age3);

        Person[] personArray = new Person[10];
        for (int i = 0; i < personArray.length; i++) {
            personArray[i] = new Person("", i);
        }

        for (Person person: personArray) {
            System.out.println(person.userAge);
        }
    }

}
