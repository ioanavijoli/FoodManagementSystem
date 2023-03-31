package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.LogManager;

public class FoodDeliveryManagementSystem extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        //LocalDateTime now = LocalDateTime.now();
      //  System.out.println(now.getDayOfWeek());
        LogManager.getLogManager().reset();
        primaryStage = stage;
        primaryStage.setTitle("Food Delivery Management System");
        FoodDeliveryManagementSystem.jumpToView("log_in.fxml",742,458);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void jumpToView(String view){
        jumpToView(view,400,500);
    }
    public static void jumpToView(String view, int xSize, int ySize){
        try {
            URL url = FoodDeliveryManagementSystem.class.getResource(view);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load(), xSize, ySize);
            primaryStage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void newStage(String view, int xSize, int ySize) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FoodDeliveryManagementSystem.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), xSize, ySize);
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.show();
    }
}

