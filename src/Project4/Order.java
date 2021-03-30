package Project4;

import java.util.ArrayList;

public class Order implements Customizable {
    private ArrayList<MenuItem> order;
    private int orderNumber;
    private double subtotal;

    public Order() {
        order = new ArrayList<MenuItem>();
    }

    public Order(int orderNumber) {
        order = new ArrayList<MenuItem>();
        this.orderNumber = orderNumber;
    }

    public Order (Order o) {
        this.order = o.order;
        this.orderNumber = o.orderNumber;
        this.subtotal = o.subtotal;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public boolean add(Object obj) {
        if (obj instanceof Coffee) {
            Coffee newCoffee = (Coffee) obj;
            newCoffee.itemPrice();
            order.add(newCoffee);
            subtotal += newCoffee.getPrice();
            return true;
        }
        else if (obj instanceof Donut) {
            Donut newDonut = (Donut) obj;
            newDonut.itemPrice();
            order.add(newDonut);
            subtotal += newDonut.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Coffee) {
            Coffee newCoffee = (Coffee) obj;
            newCoffee.itemPrice();
            order.remove(newCoffee);
            subtotal -= newCoffee.getPrice();
            return true;
        }
        else if (obj instanceof Donut) {
            Donut newDonut = (Donut) obj;
            newDonut.itemPrice();
            order.remove(newDonut);
            subtotal -= newDonut.getPrice();;
            return true;
        }
        return false;
    }

    public ArrayList<MenuItem> getOrder() {
        return order;
    }

    public double getSubtotal() {
        return subtotal;
    }
    public void clearOrder() {
        order.clear();
    }

    @Override
    public String toString() {
        String orderDetails = "Order #" + orderNumber + ": ";
        for (MenuItem m : order) {
            orderDetails += m.toString() + "; ";
        }
        return orderDetails;
    }
}
