package section3.lecture15;

public class Student extends Person {

    private String gpa;

    public Student(String name, String birthDate, String gpa) {
        super(name, birthDate);
        this.gpa = gpa;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("And my gpa is " + gpa);
    }
}
 