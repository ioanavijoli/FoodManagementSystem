package Controller;

import BusinessLogic.MenuItem;
import DataAccess.FileWriter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EmployeeNotifier implements Observer {
    public static String textToDisplay = "";
    @Override
    public void update(Observable o, Object products) {
        ArrayList<MenuItem> myOrderedProductsList = (ArrayList<MenuItem>) products;
        if(textToDisplay != "")
            textToDisplay += "\nNEW ORDER:\n";
        else
            textToDisplay += "NEW ORDER:\n";
        for(MenuItem menuItem: myOrderedProductsList)
            textToDisplay +=FileWriter.writeProduct(menuItem) + "\n";
    }
}
