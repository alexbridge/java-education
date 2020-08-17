package section3.lecture13;

public class Person {

    private String userName;
    private String password;
    private int userAge;
    private double money;

    public Person(String userName, String password, int userAge) {
        this.userName = userName;
        this.password = password;
        this.userAge = userAge;
    }

    public void setMoney(double money, String password) {
        if(password.equals(this.password)) {
            this.money = money;
        } else {
            System.out.println("Your password is incorrect");
        }
    }

    public double getMoney() {
        return money;
    }
}
