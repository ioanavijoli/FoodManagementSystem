package DataAccess;

import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import Controller.LogInController;
import Model.Client;
import Model.User;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class FileWriter {
    public static String writeOrder(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Order: " + order.getOderID() + " Client ID: " + order.getClientID() + " Date and time: " + order.getOrderDate().format(formatter) + "\n";
    }

    public static String writeProduct(MenuItem menuItem) {
        return "Product: " + menuItem.getTitle() + " Rating: " + menuItem.getRating() + " Calories: " + menuItem.getCalories()
                + " Proteins: " + menuItem.getProteins() + " Fats: " + menuItem.getFats() + " Sodium: " + menuItem.getSodium()
                + " PRICE: " + menuItem.getPrice();
    }

    public static String writeClient(int clientID) {
        User myClient = null;
        for (User client : LogInController.users) {
            if (client.getID() == clientID)
                myClient = client;
        }
        return "Client " + clientID + " Username: " + myClient.getUsername() + "\n";
    }


    public static void writeToFile(String textToDisplay, String fileName) {
        PrintWriter file = null;
        try {
            file = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        file.println(textToDisplay);
        file.close();
    }
}
