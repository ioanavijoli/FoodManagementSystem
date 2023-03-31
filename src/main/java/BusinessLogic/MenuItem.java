package BusinessLogic;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    public abstract String getTitle();
    public abstract int getPrice();
    public abstract double getRating();
    public abstract int getCalories();
    public abstract int getProteins();
    public abstract int getFats();
    public abstract int getSodium();
}
