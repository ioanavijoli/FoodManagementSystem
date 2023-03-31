package Controller;

import Application.FoodDeliveryManagementSystem;
import BusinessLogic.BaseProduct;
import BusinessLogic.MenuItem;
import DataAccess.Serializator;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import javax.swing.*;


public class AddProductController {
    @FXML
    TextField title;
    @FXML
    Spinner<Double> rating;
    @FXML
    Spinner<Integer> calories;
    @FXML
    Spinner<Integer> proteins;
    @FXML
    Spinner<Integer> fats;
    @FXML
    Spinner<Integer> sodium;
    @FXML
    TextField price;
    public static TextField staticTitle;
    public static Spinner<Double> staticRating;
    public static Spinner<Integer> staticCalories;
    public static Spinner<Integer> staticProteins;
    public static Spinner<Integer> staticFats;
    public static Spinner<Integer> staticSodium;
    public static TextField staticPrice;
    public static String oldProductName;
    public static boolean editProduct = false;
    public static int index = 0;

    public SpinnerValueFactory<Integer> getValueFactory(int min, int max) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setValue(min);
        return valueFactory;
    }

    public void initialize() {
        if (editProduct == true) {
            staticTitle = title;
            staticRating = rating;
            staticCalories = calories;
            staticProteins = proteins;
            staticFats = fats;
            staticSodium = sodium;
            staticPrice = price;
        }
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 5.0);
        valueFactory.setValue(1.0);
        rating.setValueFactory(valueFactory);
        calories.setValueFactory(getValueFactory(0, 1000));
        proteins.setValueFactory(getValueFactory(0, 1000));
        fats.setValueFactory(getValueFactory(0, 1000));
        sodium.setValueFactory(getValueFactory(0, 1000));
    }

    @FXML
    public void onBackClick() {
        FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
    }

    @FXML
    public void onSaveClick() {
        if (!price.getText().matches("[0-9]+") || price.getText() == null)
            JOptionPane.showMessageDialog(new JFrame(), "Invalid price", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        else {
            MenuItem baseProduct = new BaseProduct(title.getText(), rating.getValue(), calories.getValue(), proteins.getValue(), fats.getValue(), sodium.getValue(), Integer.valueOf(price.getText()));
            String fileName = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\menu.txt";
            if (editProduct == true) {
                AdminController.deliveryService.getMenu().set(index, baseProduct);
            } else {
                AdminController.deliveryService.getMenu().add(baseProduct);
            }
            Serializator.serialization(AdminController.deliveryService.getMenu(), fileName);
            FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
        }
    }
}
