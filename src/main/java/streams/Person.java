package streams;

public class Person {

    private String name;
    private String birthDate;

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public void printInfo() {
        System.out.println("I am a person" + " and i was born on " + birthDate );
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
