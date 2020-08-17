package section3.lecture15;

public class Teacher extends Person {

    private String school;
    private double salary;

    public Teacher(String name, String birthDate, String school, double salary) {
        super(name, birthDate);
        this.school = school;
        this.salary = salary;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("I am a teacher and I was born on " + getBirthDate());
    }
}
 