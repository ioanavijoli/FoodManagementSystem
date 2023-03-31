package Model;

public class Employee extends User{
    public Employee(int ID, String username, String password) {
        super(ID, username, password, UserType.EMPLOYEE);
    }
}
