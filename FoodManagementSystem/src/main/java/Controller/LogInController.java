package Controller;

import Application.FoodDeliveryManagementSystem;
import DataAccess.Serializator;
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LogInController implements Initializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label invalidUsername;
    public static ArrayList<User> users = new ArrayList<>();
    String fileName = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\users.txt";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Object myUsers = Serializator.deserialization(fileName);
        users = (ArrayList<User>) myUsers;
    }

    @FXML
    public void onLogInClick() {
        if (users == null) {
            invalidUsername.setVisible(true);
            return;
        }
        for (User user : users)
            if (username.getText().matches(user.getUsername()) && password.getText().matches(user.getPassword())) {
                if (user.getRole().equals(UserType.ADMIN))
                    FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
                if (user.getRole().equals(UserType.CLIENT)) {
                    Client myClient = new Client(user.getID(), user.getUsername(), user.getPassword());
                    ClientController.client = myClient;
                    FoodDeliveryManagementSystem.jumpToView("client_view.fxml", 895, 539);
                }
                if (user.getRole().equals(UserType.EMPLOYEE))
                    FoodDeliveryManagementSystem.jumpToView("employee_view.fxml", 765, 567);
            } else
                invalidUsername.setVisible(true);

    }

    @FXML
    public void onRegisterClick() {
        FoodDeliveryManagementSystem.jumpToView("sign_up_view.fxml", 742, 458);
    }

}
