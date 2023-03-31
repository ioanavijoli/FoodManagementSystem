package BusinessLogic;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Vijoli Ioana
 */
public interface IDeliveryServiceProcessing {
    /**
     * @return base products imported from a CSV file
     * @throws IOException exception
     */

    List<BaseProduct> importProducts() throws IOException;

    /**
     * @param name product title
     * @return products with specific title
     */
    ArrayList<MenuItem> searchByName(String name);

    /**
     * @param rating product rating
     * @pre rating>0
     * @return products with specific rating
     */
    ArrayList<MenuItem> searchByRating(Double rating);

    /**
     * @param calories product calories
     * @return a list of products which contain the specified number of calories
     */

    ArrayList<MenuItem> searchByCalories(int calories);

    /**
     * @param proteins product proteins
     * @return a list of products which contain the specified number of proteins
     */

    ArrayList<MenuItem> searchByProteins(int proteins);

    /**
     * @param fats product fats
     * @return a list of products which contain the specified number of fats
     */

    ArrayList<MenuItem> searchByFats(int fats);

    /**
     * @param sodium product sodium
     * @return a list of products which contain the specified amount of sodium
     */

    ArrayList<MenuItem> searchBySodium(int sodium);

    /**
     * @param price product price
     * @return a list of products which have the specified price
     */

    ArrayList<MenuItem> searchByPrice(int price);

    /**
     * @param startHour given start hour
     * @param endHour   given end hour
     * @return orders performed
     * between the given start hour and the given end hour regardless the date.
     */

    List<Map.Entry<Order, ArrayList<MenuItem>>> searchByHour(int startHour, int endHour);

    /**
     * @param menuItem a product from Menu
     * @return number of times the product was ordered
     */

    int nrOfTimesProductOrdered(MenuItem menuItem);

    /**
     * @param N number of times
     * @return a list of products ordered more than N times
     */

    List<MenuItem> searchProductsOrderedMoreThanNTimes(int N);

    /**
     * @param menuItems an order
     * @return total price of specified order
     */

    int priceOfOrder(Map.Entry<Order, ArrayList<MenuItem>> menuItems);

    /**
     * @param clientID client ID
     * @return number of times this client ordered
     */

    int nrOfTimesClientOrdered(int clientID);

    /**
     * @param N      number of orders
     * @param amount total minumum price
     * @return the clients IDs that have ordered more than a specified number of times so far and the
     * value of the order was higher than a specified amount.
     */

    List<Integer> searchByNrOfOrders(int N, int amount);

    /**
     * @param day day of week
     * @return a list of products ordered within the specified day
     */

    List<MenuItem> searchProductsOrderOnSpecificDay(String day);

    /**
     * @param products the products of the current order
     */

    void notifyEmployee(ArrayList<MenuItem> products);
}
