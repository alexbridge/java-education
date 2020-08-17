package section3.lecture15;

public class SubclassesAdvanced {

    public static void main(String[] args) {

        Student student1 = new Student("Willy", "April 20, 2000", "410");
        student1.printInfo();

        Person person1 = new Person("Joey", "April 10, 1990");
        person1.printInfo();

        Teacher teacher1 = new Teacher("Wendy", "April 10, 1990", "UCLA", 15000);
        teacher1.printInfo();

        Teacher teacher2 = new Teacher("Willy", "April 20, 2000", "Caltech", 15000);
        teacher2.printInfo();

        Person[] personArray = {
                student1, person1, teacher1, teacher2
        };

        for (Person person: personArray) {
            person.printInfo();
        }
    }
}
