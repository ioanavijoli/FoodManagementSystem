package Controller;

import Application.FoodDeliveryManagementSystem;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import DataAccess.FileWriter;
import Model.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static DataAccess.FileWriter.*;


public class GenerateReportsController implements Initializable {
    @FXML
    ComboBox startHour;
    @FXML
    ComboBox endHour;
    @FXML
    TextField nrProducts;
    @FXML
    TextField nrOrders;
    @FXML
    TextField valueOfOrder;
    @FXML
    ComboBox<String> day;
    List<Map.Entry<Order, ArrayList<MenuItem>>> orderInformation = null;
    String fileName = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\reports\\report";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i <= 23; i++) {
            startHour.getItems().addAll(i);
            endHour.getItems().add(i);
        }
        startHour.getSelectionModel().selectFirst();
        endHour.getSelectionModel().selectFirst();
        day.getItems().addAll("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY");
        day.getSelectionModel().selectFirst();
    }

    @FXML
    public void onGenerateReport1() {
        String[] textToDisplay = new String[1];
        textToDisplay[0] = "";
        if ((int) startHour.getSelectionModel().getSelectedItem() > (int) endHour.getSelectionModel().getSelectedItem())
            JOptionPane.showMessageDialog(new JFrame(), "Start hour must be lower than end hour", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        else {
            AdminController.deliveryService.getOrderInformation().size();
            orderInformation = AdminController.deliveryService.searchByHour((int) startHour.getSelectionModel().getSelectedItem(), (int) endHour.getSelectionModel().getSelectedItem());
            if (orderInformation.size() != 0) {
                orderInformation.forEach(order -> textToDisplay[0] = textToDisplay[0] + writeOrder(order.getKey()));
                FileWriter.writeToFile(textToDisplay[0], fileName + "1");
                JOptionPane.showMessageDialog(new JFrame(), "Report generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(new JFrame(), "There are no orders performed within the given time range", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    public void onGenerateReport2() {
        String[] textToDisplay = new String[1];
        textToDisplay[0] = "";

        if (!nrProducts.getText().matches("[0-9]+"))
            JOptionPane.showMessageDialog(new JFrame(), "Enter a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        else {
            int N = Integer.valueOf(nrProducts.getText());
            List<MenuItem> menuItems = AdminController.deliveryService.searchProductsOrderedMoreThanNTimes(N);
            if (menuItems.size() != 0) {
                menuItems.forEach(menuItem -> textToDisplay[0] = textToDisplay[0] + writeProduct(menuItem) + "\n");
                FileWriter.writeToFile(textToDisplay[0], fileName + "2");
                JOptionPane.showMessageDialog(new JFrame(), "Report generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(new JFrame(), "There are no products ordered more than N times", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    @FXML
    public void onGenerateReport3() {
        String textToDisplay = "";
        if (!nrOrders.getText().matches("[0-9]+") || !valueOfOrder.getText().matches("[0-9]+"))
            JOptionPane.showMessageDialog(new JFrame(), "Enter valid numbers", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        else {
            List<Integer> myClientsIDs = AdminController.deliveryService.searchByNrOfOrders(Integer.valueOf(nrOrders.getText()), Integer.valueOf(valueOfOrder.getText()));
            if (myClientsIDs.size() != 0) {
                for (Integer ID : myClientsIDs)
                    textToDisplay += writeClient(ID);
                FileWriter.writeToFile(textToDisplay, fileName + "3");
                JOptionPane.showMessageDialog(new JFrame(), "Report generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(new JFrame(), "There are no customers who fulfill the condition", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    public void onGenerateReport4() {
        String textToDisplay = "";
        List<MenuItem> myProducts = AdminController.deliveryService.searchProductsOrderOnSpecificDay(day.getSelectionModel().getSelectedItem());
        if (myProducts.size() != 0) {
            for (MenuItem menuItem : myProducts.stream().distinct().collect(Collectors.toList()))
                textToDisplay += writeProduct(menuItem) + " Ordered: " + AdminController.deliveryService.nrOfTimesProductOrdered(menuItem) + " times\n";
            FileWriter.writeToFile(textToDisplay, fileName + "4");
            JOptionPane.showMessageDialog(new JFrame(), "Report generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "There are no products ordered on specified day", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    public void onBackClick() {
        FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
    }
}
