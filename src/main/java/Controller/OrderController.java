package Controller;

import Application.FoodDeliveryManagementSystem;
import BusinessLogic.BaseProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import DataAccess.FileWriter;
import DataAccess.Serializator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;


public class OrderController {
    @FXML
    TextField totalPrice;
    @FXML
    TextArea textArea;
    String fileName = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\orders.txt";
    static final EmployeeNotifier employeeNotifier = new EmployeeNotifier();

    public int getCurrentPrice() {
        int price = 0;
        for (MenuItem menuItem : ClientController.orderedProducts)
            price += menuItem.getPrice();
        return price;
    }

    public String listSelectedProducts() {
        String products = "";
        for (MenuItem menuItem : ClientController.orderedProducts)
            products = products + menuItem.getTitle() + "   " + menuItem.getRating() + "    " + menuItem.getCalories() + "    "
                    + menuItem.getProteins() + "    " + menuItem.getFats() + "    " + menuItem.getSodium() + "    " + menuItem.getPrice() + "\n";
        return products;
    }

    public void initialize() {
        totalPrice.setText(Integer.toString(getCurrentPrice()));
        textArea.appendText(listSelectedProducts());
    }

    @FXML
    public void onChooseOtherProductsClick() {
        FoodDeliveryManagementSystem.jumpToView("client_view.fxml", 895, 539);
    }

    @FXML
    public void onSaveOrderClick() {
        int orderID = 0;
        String file = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\bills\\bill_order" + System.currentTimeMillis() + ".txt";
        Object orders = Serializator.deserialization(fileName);
        HashMap<Order, ArrayList<MenuItem>> myOrders = (HashMap<Order, ArrayList<MenuItem>>) orders;
        String textToDisplay = "";
        if (ClientController.orderedProducts.size() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "The cart is empty", "Error", JOptionPane.ERROR_MESSAGE);
            FoodDeliveryManagementSystem.jumpToView("client_view.fxml", 895, 539);
        } else {

            if (orders == null)
                orderID = 0;
            else {
                AdminController.deliveryService.setOrderInformation(myOrders);
                for (Map.Entry<Order, ArrayList<MenuItem>> entry : AdminController.deliveryService.getOrderInformation().entrySet())
                    orderID = entry.getKey().getOderID();
            }
            Order order = new Order(orderID + 1, ClientController.client.getID(), LocalDateTime.now());
            AdminController.deliveryService.getOrderInformation().put(order, ClientController.orderedProducts);
            Serializator.serialization(AdminController.deliveryService.getOrderInformation(), fileName);
            for (MenuItem menuItem : ClientController.orderedProducts)
                textToDisplay += FileWriter.writeProduct(menuItem) + "\n";
            FileWriter.writeToFile(textToDisplay + "TOTAL PRICE = " + getCurrentPrice(), file);
            JOptionPane.showMessageDialog(new JFrame(), "Order added", "Success", JOptionPane.INFORMATION_MESSAGE);
            AdminController.deliveryService.addObserver(employeeNotifier);
            AdminController.deliveryService.notifyEmployee(ClientController.orderedProducts);
            ClientController.orderedProducts.removeAll(ClientController.orderedProducts);
            FoodDeliveryManagementSystem.jumpToView("log_in.fxml", 742, 458);
        }
    }

    @FXML
    public void onDeleteProductsClick() {
        ClientController.orderedProducts.removeAll(ClientController.orderedProducts);
        textArea.setText("");
        totalPrice.setText("");
    }
}
