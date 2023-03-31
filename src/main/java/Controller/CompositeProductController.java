package Controller;

import Application.FoodDeliveryManagementSystem;
import BusinessLogic.CompositeProduct;
import BusinessLogic.MenuItem;
import DataAccess.Serializator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;

import java.util.ResourceBundle;

public class CompositeProductController implements Initializable {
    @FXML
    TextArea textArea;
    @FXML
    TextField name;
    public static CompositeProduct compositeProduct = new CompositeProduct();

    public String listSelectedProducts() {
        String products = "";
        for (MenuItem menuItem : (compositeProduct).getMenuItems())
            products = products + menuItem.getTitle() + "   " + menuItem.getRating() + "    " +  menuItem.getCalories() + "    "
                    + menuItem.getProteins() + "    " + menuItem.getFats() + "    " + menuItem.getSodium() + "    "  + menuItem.getPrice() + "\n";
        return products;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setText(listSelectedProducts());
    }

    @FXML
    public void onSaveComposedProductClick() {
        compositeProduct.setTitle(name.getText());
        String fileName = "C:\\Users\\Ioana\\Desktop\\pt2022_30222_vijoli_ioana_assignment_4\\src\\menu.txt";
        AdminController.deliveryService.getMenu().add(compositeProduct);
        Serializator.serialization(AdminController.deliveryService.getMenu(), fileName);
        FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
    }

    @FXML
    public void onChooseOtherProductsClick() {
        FoodDeliveryManagementSystem.jumpToView("admin_view.fxml", 895, 539);
    }

}
