package Controller;

import Application.FoodDeliveryManagementSystem;
import BusinessLogic.CompositeProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import DataAccess.Serializator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
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
    static DeliveryService deliveryService = new DeliveryService();
    String fileMenu = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\menu.txt";
    String fileOrders = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\orders.txt";

    public void updateTable(){
        deliveryService.setMenu((ArrayList<MenuItem>) Serializator.deserialization(fileMenu));
        ArrayList<MenuItem> menu = new ArrayList<>(deliveryService.getMenu());
        setTable(title, rating, calories, protein, fat, sodium, price, menu, table);
    }

    static void setTable(TableColumn<MenuItem, String> title, TableColumn<MenuItem, Double> rating, TableColumn<MenuItem, Integer> calories, TableColumn<MenuItem, Integer> protein, TableColumn<MenuItem, Integer> fat, TableColumn<MenuItem, Integer> sodium, TableColumn<MenuItem, Integer> price, ArrayList <MenuItem> menu, TableView<MenuItem> table) {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        protein.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        fat.setCellValueFactory(new PropertyValueFactory<>("fats"));
        sodium.setCellValueFactory(new PropertyValueFactory<>("sodium"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<? extends MenuItem> productList =  FXCollections.observableList(menu);
        table.setItems((ObservableList<MenuItem>) productList);
        table.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        updateTable();
        HashMap<Order, ArrayList<MenuItem>> deserialized = (HashMap<Order, ArrayList<MenuItem>>) Serializator.deserialization(fileOrders);
        deliveryService.setOrderInformation(deserialized);
    }

    @FXML
    public void importProducts() throws IOException {
        deliveryService.getMenu().addAll(deliveryService.importProducts());
        Serializator.serialization(deliveryService.getMenu(), fileMenu);
        FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
    }

    @FXML
    public void onLogOutClick() {
        FoodDeliveryManagementSystem.jumpToView("log_in.fxml", 742, 458);
    }

    @FXML
    public void onGenerateClick() {
        FoodDeliveryManagementSystem.jumpToView("generate_reports.fxml", 869, 638);
    }

    @FXML
    public void onAddProductClick() {
        AddProductController.editProduct = false;
        FoodDeliveryManagementSystem.jumpToView("add_product.fxml", 647, 444);
    }

    @FXML
    public void onEditProductClick() {
        AddProductController.editProduct = true;
        int index = table.getSelectionModel().getSelectedIndex();
        AddProductController.index = index;
        FoodDeliveryManagementSystem.jumpToView("add_product.fxml", 647, 444);
        AddProductController.oldProductName = String.valueOf(title.getCellData(index));
        AddProductController.staticTitle.setText(String.valueOf(title.getCellData(index)));
        AddProductController.staticRating.getValueFactory().setValue(Double.valueOf(String.valueOf(rating.getCellData(index))));
        AddProductController.staticCalories.getValueFactory().setValue(Integer.valueOf(String.valueOf(calories.getCellData(index))));
        AddProductController.staticProteins.getValueFactory().setValue(Integer.valueOf(String.valueOf(protein.getCellData(index))));
        AddProductController.staticFats.getValueFactory().setValue(Integer.valueOf(String.valueOf(fat.getCellData(index))));
        AddProductController.staticSodium.getValueFactory().setValue(Integer.valueOf(String.valueOf(sodium.getCellData(index))));
        AddProductController.staticPrice.setText(String.valueOf(price.getCellData(index)));
    }

    @FXML
    public void onDeleteClick() {
        int index = table.getSelectionModel().getSelectedIndex();
        deliveryService.getMenu().remove(index);
        Serializator.serialization(deliveryService.getMenu(), fileMenu);
        updateTable();

    }
    @FXML
    public void onCreateCompositeProductClick(){
        CompositeProductController.compositeProduct = new CompositeProduct(new ArrayList<>(table.getSelectionModel().getSelectedItems()), null);
        FoodDeliveryManagementSystem.jumpToView("composite_menu.fxml", 680, 482);
    }

}
