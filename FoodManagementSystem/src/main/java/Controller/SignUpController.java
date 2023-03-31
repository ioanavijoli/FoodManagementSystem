package Controller;

import Application.FoodDeliveryManagementSystem;
import DataAccess.Serializator;
import Model.User;
import Model.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.ArrayList;

public class SignUpController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    PasswordField confirmPassword;
    @FXML
    Label notMatchLabel;
    @FXML
    ComboBox<UserType> role;
    static ArrayList<User> users = new ArrayList<>();
    String fileName = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\users.txt";

    @FXML
    public void initialize() {
        notMatchLabel.setVisible(false);
        role.getItems().addAll(UserType.ADMIN, UserType.CLIENT, UserType.EMPLOYEE);
        role.getSelectionModel().selectFirst();
    }

    @FXML
    public void onSignUpClick() {
        if (username.getText() == "" || password.getText() == "") {
            notMatchLabel.setVisible(true);
            notMatchLabel.setText("Some fields are empty");
        } else if (!password.getText().matches(confirmPassword.getText())) {
            notMatchLabel.setVisible(true);
            notMatchLabel.setText("Passwords don't match");
        } else {
            Object myUsers = Serializator.deserialization(fileName);
            int userID = 0;
            if (myUsers == null)
                userID = 0;
            else {
                ArrayList<User> userList = (ArrayList<User>) myUsers;
                for (User myClient : userList)
                    userID = myClient.getID();
                users = userList;
            }
            User user = new User(userID + 1,username.getText(), password.getText(), role.getSelectionModel().getSelectedItem());
            users.add(user);
            Serializator.serialization(users,fileName);
            JOptionPane.showMessageDialog(new JFrame(), "Account created", "Success", JOptionPane.INFORMATION_MESSAGE);
            FoodDeliveryManagementSystem.jumpToView("log_in.fxml", 742, 458);
        }
    }
}
