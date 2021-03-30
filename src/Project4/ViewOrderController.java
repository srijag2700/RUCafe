package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


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

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    @FXML
    public void setOrder(Order o) {
        this.o = o;
        observableOrder = FXCollections.observableArrayList(o.getOrder());
        currentOrderList.setItems(observableOrder);

        subtotal.setText("" + o.getSubtotal());
        salesTaxValue = 0.06625 * o.getSubtotal();
        salesTax.setText("" + salesTaxValue);
        totalValue = o.getSubtotal() + salesTaxValue;
        total.setText("" + totalValue);

        currentOrderList.getItems().addListener((ListChangeListener) change -> {
            subtotal.setText("" + o.getSubtotal());
            salesTaxValue = 0.06625 * o.getSubtotal();
            salesTax.setText("" + salesTaxValue);
            totalValue = o.getSubtotal() + salesTaxValue;
            total.setText("" + totalValue);
        });
    }

    @FXML
    public void setAllStoreOrders(StoreOrders s) {
        this.s = s;
    }

    @FXML
    public void removeItem(ActionEvent event) {
        if (currentOrderList.getSelectionModel().getSelectedItem() != null) {
            o.remove(currentOrderList.getSelectionModel().getSelectedItem());
            currentOrderList.getItems().remove(currentOrderList.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void placeOrder(ActionEvent event) {
        Order finalOrder = new Order(o);
        s.add(finalOrder);
        mmController.order = new Order();
    }
}
