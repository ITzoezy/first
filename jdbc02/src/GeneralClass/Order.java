package GeneralClass;

import java.sql.Date;

public class Order {

    private int orderId;
    private String orderName;
    private Date date;

    public Order() {
    }

    public Order(int orderId, String orderName, Date date) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", date=" + date +
                '}';
    }
}
