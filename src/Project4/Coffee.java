package Project4;

public class Coffee extends MenuItem implements Customizable {
    public static final double SHORT_PRICE = 1.99;
    public static final double TALL_PRICE = 2.49;
    public static final double GRANDE_PRICE = 2.99;
    public static final double VENTI_PRICE = 3.49;
    String size = "";
    // array list for add-ins?

    public boolean add(Object obj) {
        return false;
    }

    public boolean remove(Object obj) {
        return false;
    }

    @Override
    public void itemPrice() {
        // check to make sure this is only done once
        if (size.equals("Short")) {
            price = SHORT_PRICE;
        }
        else if (size.equals("Tall")) {
            price = TALL_PRICE;
        }
        else if (size.equals("Grande")) {
            price = GRANDE_PRICE;
        }
        else if (size.equals("Venti")) {
            price = VENTI_PRICE;
        }

        // something for add ins

    }
}
