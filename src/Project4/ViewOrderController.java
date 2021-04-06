package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

/**
 * This class represents the controller for the View Order menu.
 * It controls the functions of viewing the items in the current order and their subtotal, tax, and total, removing selected items from the order, and placing the order.
 * @author Srija Gottiparthi, Catherine Nguyen
 */


public class ViewOrderController {

    @FXML
    private MainMenuController mmController;

    @FXML
    private ListView currentOrderList;

    @FXML
    private TextField subtotal, salesTax, total;

    private ObservableList<MenuItem> observableOrder;

    private static Order o;
    private static StoreOrders s;
    private double salesTaxValue, totalValue;

    private DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    /**
     * Connects the Main Menu controller to this controller so that coffee can be added to the order.
     * @param controller the Main Menu controller associated with this controller
     */
    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    /**
     * Set the order variable from the Main Menu controller.
     * @param o the current Order object
     */
    @FXML
    public void setOrder(Order o) {
        this.o = o;
        observableOrder = FXCollections.observableArrayList(o.getOrder());
        currentOrderList.setItems(observableOrder);

        subtotal.setText("" + df.format(o.getSubtotal()));
        salesTaxValue = 0.06625 * o.getSubtotal();
        salesTax.setText("" + df.format(salesTaxValue));
        totalValue = o.getSubtotal() + salesTaxValue;
        total.setText("" + df.format(totalValue));

        currentOrderList.getItems().addListener((ListChangeListener) change -> {
            subtotal.setText("" + df.format(o.getSubtotal()));
            salesTax.setText("" + df.format(o.getTax()));
            total.setText("" + df.format(o.getTotal()));
        });
    }

    /**
     * Set the store orders variable from the Main Menu controller.
     * @param s the current StoreOrders object
     */
    @FXML
    public void setAllStoreOrders(StoreOrders s) {
        this.s = s;
    }

    /**
     * Removes the selected item from the current order.
     * @param event the "Remove Selected Item" button getting clicked
     */
    @FXML
    public void removeItem(ActionEvent event) {
        if (currentOrderList.getSelectionModel().getSelectedItem() != null) {
            o.remove(currentOrderList.getSelectionModel().getSelectedItem());
            currentOrderList.getItems().remove(currentOrderList.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Places the current order and adds it to the StoreOrders.
     * @param event the "Place Order" button getting clicked
     */
    @FXML
    public void placeOrder(ActionEvent event) {
        if (observableOrder.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Order");
            alert.setHeaderText("Please add items to your order.");
            alert.showAndWait();
        }
        else {
            Order finalOrder = new Order(o);
            s.add(finalOrder);
            observableOrder.clear();
            subtotal.clear();
            salesTax.clear();
            total.clear();
            mmController.order = new Order();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText("Your order has been placed!");
            alert.showAndWait();
        }
    }
}
