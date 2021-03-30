package Project4;

public class Donut extends MenuItem {
    private String type; // yeast, cake, or donut hole
    private String flavor; // the flavor
    private int quantity;
    private double price;

    private final static double YEAST_PRICE = 1.39;
    private final static double CAKE_PRICE = 1.59;
    private final static double HOLE_PRICE = 0.33;

    public Donut(String type, String flavor, int quantity) {
        super();
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    public void itemPrice() {
        if (type.equals("Yeast Donut")) {
            price = YEAST_PRICE;
        }
        else if (type.equals("Cake Donut")) {
            price = CAKE_PRICE;
        }
        else {
            price = HOLE_PRICE;
        }

        price *= quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return (type + "(" + quantity + "), " + flavor);
    }
}
