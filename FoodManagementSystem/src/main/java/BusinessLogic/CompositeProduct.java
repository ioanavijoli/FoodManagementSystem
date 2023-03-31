package BusinessLogic;

import java.util.ArrayList;
import java.util.Objects;

public class CompositeProduct extends MenuItem {
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private String title;
    public CompositeProduct(ArrayList<MenuItem> menuItems, String title) {
        this.menuItems = menuItems;
        this.title = title;
    }

    public CompositeProduct(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeProduct that = (CompositeProduct) o;
        return Objects.equals(menuItems, that.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItems);
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public int getPrice() {
        int price = 0;
        for (MenuItem menuItem : menuItems)
            price += menuItem.getPrice();
        return price;
    }

    public double getRating() {
        double rating = 0;
        for (MenuItem menuItem : menuItems)
            rating += menuItem.getRating();
        return rating / menuItems.size();
    }

    @Override
    public int getCalories() {
        int calories = 0;
        for (MenuItem menuItem : menuItems)
            calories += menuItem.getCalories();
        return calories;
    }

    @Override
    public int getProteins() {
        int proteins = 0;
        for (MenuItem menuItem : menuItems)
            proteins += menuItem.getProteins();
        return proteins;
    }

    @Override
    public int getFats() {
        int fats = 0;
        for (MenuItem menuItem : menuItems)
            fats += menuItem.getFats();
        return fats;
    }

    @Override
    public int getSodium() {
        int sodium = 0;
        for(MenuItem menuItem: menuItems)
            sodium += menuItem.getProteins();
        return sodium;
    }
}
