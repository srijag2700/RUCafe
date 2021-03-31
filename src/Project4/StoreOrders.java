package Project4;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoreOrders implements Customizable{
    private ArrayList<Order> store;
    private final static int STARTING_ORDER_NUMBER = 1;

    public StoreOrders() {
        store = new ArrayList<Order>();
    }

    public int getLatestOrderNumber() {
        int latestOrderNumber = STARTING_ORDER_NUMBER;
        for (Order o : store) {
            if (o.getOrderNumber() > latestOrderNumber) {
                latestOrderNumber = o.getOrderNumber();
            }
        }
        return latestOrderNumber;
    }

    @Override
    public boolean add(Object obj) {
        Order newOrder = (Order) obj;
        if (store.isEmpty()) {
            newOrder.setOrderNumber(STARTING_ORDER_NUMBER);
        }
        else {
            newOrder.setOrderNumber(getLatestOrderNumber() + 1);
        }
        return store.add(newOrder);
    }

    @Override
    public boolean remove(Object obj) {
        Order orderToRemove = (Order) obj;
        return store.remove(orderToRemove);
    }

    @Override
    public String toString() {
        String allOrders = "";
        for (Order o : store) {
            allOrders += (o.toString() + "\n");
        }
        return allOrders;
    }

    public ArrayList<Order> getStore() {
        return store;
    }

    public Order getOrderByOrderNumber(int n) {
        for (Order o : store) {
            if (o.getOrderNumber() == n) {
                return o;
            }
        }
        return null;
    }

    /**
     * Exports the current list of orders as a text file.
     * @throws IOException if there is an error in file selection
     */
    public void exportAllOrders() throws IOException {

        String database = toString();

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose File to Export To");
        chooser.setInitialFileName("allOrders.txt");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);

        FileWriter fw = new FileWriter(targetFile);
        fw.write(database);
        fw.close();
    }

}
