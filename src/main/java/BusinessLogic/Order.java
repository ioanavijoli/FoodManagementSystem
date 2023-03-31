package BusinessLogic;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int oderID;
    private int clientID;
    private LocalDateTime orderDate;
    public Order(int oderID, int clientID, LocalDateTime orderDate) {
        this.oderID = oderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    public Order(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return oderID == order.oderID && clientID == order.clientID && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oderID, clientID, orderDate);
    }

    public int getOderID() {
        return oderID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

}
