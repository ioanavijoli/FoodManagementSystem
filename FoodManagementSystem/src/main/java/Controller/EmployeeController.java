package Controller;

import Application.FoodDeliveryManagementSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class EmployeeController {
    @FXML
    TextArea textArea;
    public void initialize(){
        textArea.setText(EmployeeNotifier.textToDisplay);
    }
    @FXML
    public void onLogOutClick() {
        FoodDeliveryManagementSystem.jumpToView("log_in.fxml", 742, 458);
    }
}
