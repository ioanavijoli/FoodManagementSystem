package Controller;

import Application.FoodDeliveryManagementSystem;
import BusinessLogic.MenuItem;
import DataAccess.Serializator;
import Model.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    TableView<BusinessLogic.MenuItem> table;
    @FXML
    TableColumn<BusinessLogic.MenuItem, String> title;
    @FXML
    TableColumn<BusinessLogic.MenuItem, Double> rating;
    @FXML
    TableColumn<BusinessLogic.MenuItem, Integer> calories;
    @FXML
    TableColumn<BusinessLogic.MenuItem, Integer> protein;
    @FXML
    TableColumn<BusinessLogic.MenuItem, Integer> fat;
    @FXML
    TableColumn<BusinessLogic.MenuItem, Integer> sodium;
    @FXML
    TableColumn<BusinessLogic.MenuItem, Integer> price;
    @FXML
    ComboBox searchOptions;
    @FXML
    TextField searchedValue;
    String fileDestination = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\menu.txt";
    static ArrayList<MenuItem> orderedProducts = new ArrayList<>();
    static Client client;

    public void updateTable() {
        AdminController.deliveryService.setMenu((ArrayList<MenuItem>) Serializator.deserialization(fileDestination));
        ArrayList<MenuItem> menu = new ArrayList<>(AdminController.deliveryService.getMenu());
        AdminController.setTable(title, rating, calories, protein, fat, sodium, price, menu, table);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        searchOptions.getItems().addAll("Name", "Rating", "Number of calories", "Number of proteins", "Number of fats", "Amount of sodium", "Price");
        searchOptions.getSelectionModel().selectFirst();
        searchedValue.setPromptText(searchOptions.getSelectionModel().getSelectedItem().toString());
        updateTable();
    }

    @FXML
    public void onChoiceClick() {
        searchedValue.setPromptText(searchOptions.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    public void onLogOutClick() {
        client = null;
        FoodDeliveryManagementSystem.jumpToView("log_in.fxml", 742, 458);
    }

    @FXML
    public void onSearchClick() {
        ArrayList<MenuItem> menu = new ArrayList<>();
        if (searchedValue.getText() != null) {
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Name"))
                menu = AdminController.deliveryService.searchByName(searchedValue.getText());
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Rating"))
                menu = AdminController.deliveryService.searchByRating(Double.valueOf(searchedValue.getText()));
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Number of calories"))
                menu = AdminController.deliveryService.searchByCalories(Integer.valueOf(searchedValue.getText()));
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Number of proteins"))
                menu = AdminController.deliveryService.searchByProteins(Integer.valueOf(searchedValue.getText()));
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Number of fats"))
                menu = AdminController.deliveryService.searchByFats(Integer.valueOf(searchedValue.getText()));
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Amount of sodium"))
                menu = AdminController.deliveryService.searchBySodium(Integer.valueOf(searchedValue.getText()));
            if (searchOptions.getSelectionModel().getSelectedItem().equals("Price"))
                menu = AdminController.deliveryService.searchByPrice(Integer.valueOf(searchedValue.getText()));
            AdminController.setTable(title, rating, calories, protein, fat, sodium, price, menu, table);
        }
    }

    @FXML
    public void onAddProductsToCartClick() {
        orderedProducts.addAll(table.getSelectionModel().getSelectedItems());
        JOptionPane.showMessageDialog(new JFrame(), "Products added to cart", "Products added", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void onCartClick() {
        FoodDeliveryManagementSystem.jumpToView("save_order.fxml", 680, 482);
    }


}
