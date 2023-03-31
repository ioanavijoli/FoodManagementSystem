package BusinessLogic;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    HashMap<Order, ArrayList <MenuItem>> orderInformation = new HashMap<>();
    ArrayList<MenuItem> menu = new ArrayList<>();

    public List<BaseProduct> importProducts() throws IOException {
        List<BaseProduct> baseProducts = new ArrayList<>();
        Stream<String> lines = Files.lines(Paths.get("src/products.csv")).filter(s -> !s.matches("Title,Rating,Calories,Protein,Fat,Sodium,Price"));
        assert lines.findAny().isPresent(); // the csv file is not empty
        lines.forEach(line -> {
            String[] fields = line.split(",");
            fields[0] = fields[0].replaceAll("\"", "");
            fields[0] = fields[0].replaceAll("/","");
            fields[0] = fields[0].replaceAll(":","");
            BaseProduct baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
            baseProducts.add(baseProduct);
        });
        return baseProducts;
    }

    public HashMap<Order, ArrayList<MenuItem>> getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(HashMap<Order, ArrayList<MenuItem>> orderInformation) {
        this.orderInformation = orderInformation;
    }

    public ArrayList <MenuItem> searchByName(String name){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getTitle().contains(name)).collect(Collectors.toList());
    }
    public ArrayList <MenuItem> searchByRating(Double rating){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getRating() == rating).collect(Collectors.toList());
    }
    public ArrayList <MenuItem> searchByCalories(int calories){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getCalories() == calories).collect(Collectors.toList());
    }
    public ArrayList <MenuItem> searchByProteins(int proteins){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getProteins() == proteins).collect(Collectors.toList());

    }
    public ArrayList <MenuItem> searchByFats(int fats){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getFats() == fats).collect(Collectors.toList());
    }
    public ArrayList <MenuItem> searchBySodium(int sodium){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getSodium() == sodium).collect(Collectors.toList());
    }
    public ArrayList <MenuItem> searchByPrice(int price){
        return (ArrayList<MenuItem>) menu.stream().filter(menuItem -> menuItem.getPrice() == price).collect(Collectors.toList());
    }
    public List<Map.Entry<Order, ArrayList <MenuItem>>> searchByHour(int startHour, int endHour){
        assert startHour <= endHour;
        return orderInformation.entrySet().stream().filter(order ->  order.getKey().getOrderDate().getHour() >= startHour && order.getKey().getOrderDate().getHour() <= endHour).collect(Collectors.toList());
    }
    public int nrOfTimesProductOrdered(MenuItem menuItem){
        assert menuItem != null;
        return orderInformation.entrySet().stream().filter(order -> order.getValue().contains(menuItem)).collect(Collectors.toList()).size() ;
    }
    public List<MenuItem> searchProductsOrderedMoreThanNTimes(int N){
        return menu.stream().filter(menuItem -> nrOfTimesProductOrdered(menuItem) > N).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
    public int priceOfOrder(Map.Entry<Order, ArrayList <MenuItem>> menuItems){
        final int[] price = {0};
        assert !menuItems.getValue().isEmpty();
        menuItems.getValue().forEach(product -> price[0] += product.getPrice());
        return price[0];
    }
    public int nrOfTimesClientOrdered(int clientID){
        return orderInformation.entrySet().stream().filter(order -> order.getKey().getClientID() == clientID).collect(Collectors.toList()).size();
    }
    public List<Integer> searchByNrOfOrders(int N, int amount){
        assert N != 0;
        List<Map.Entry<Order, ArrayList<MenuItem>>> list = orderInformation.entrySet().stream().filter(order -> (nrOfTimesClientOrdered(order.getKey().getClientID()) > N) && priceOfOrder(order)> amount).collect(Collectors.toList());
        List<Integer> myClients = new ArrayList<>();
        list.forEach(orderArrayListEntry -> myClients.add(orderArrayListEntry.getKey().getClientID()));
        return myClients.stream().distinct().collect(Collectors.toList());
    }
    public List<MenuItem> searchProductsOrderOnSpecificDay(String day){
        List<Map.Entry<Order, ArrayList<MenuItem>>> list = orderInformation.entrySet().stream().filter(order -> order.getKey().getOrderDate().getDayOfWeek().toString().matches(day)).collect(Collectors.toList());
        List<MenuItem> myProducts = new ArrayList<>();
        list.forEach(orderArrayListEntry -> myProducts.addAll(orderArrayListEntry.getValue()));
        return myProducts;
    }
    public void notifyEmployee(ArrayList <MenuItem> products){
        assert products != null;
        setChanged();
        notifyObservers(products);
    }
    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

}
