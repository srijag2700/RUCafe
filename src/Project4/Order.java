package Project4;

import java.util.ArrayList;

public class Order implements Customizable {
    ArrayList<MenuItem> order;

    public Order() {
        order = new ArrayList<MenuItem>();
    }

    @Override
    public boolean add(Object obj) {
        if (obj instanceof Coffee) {
            Coffee newCoffee = (Coffee) obj;
            order.add(newCoffee);
            return true;
        }
        else if (obj instanceof Donut) {
            Donut newDonut = (Donut) obj;
            order.add(newDonut);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Coffee) {
            Coffee newCoffee = (Coffee) obj;
            order.remove(newCoffee);
            return true;
        }
        else if (obj instanceof Donut) {
            Donut newDonut = (Donut) obj;
            order.remove(newDonut);
            return true;
        }
        return false;
    }

    public ArrayList<MenuItem> getOrder() {
        return order;
    }
}
