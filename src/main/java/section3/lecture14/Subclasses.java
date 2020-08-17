package section3.lecture14;

public class Subclasses {

    public static void main(String[] args) {

        Student student1 = new Student("Willy", "April 20, 2000", "410");
        student1.printInfo();

        Person person1 = new Person("Joey", "April 10, 1990");
        person1.printInfo();
    }
}
