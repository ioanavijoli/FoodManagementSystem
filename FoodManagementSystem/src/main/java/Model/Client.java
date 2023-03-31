package Model;

public class Client extends User{
    public Client(int ID, String username, String password) {
        super(ID, username, password, UserType.CLIENT);
    }
}
