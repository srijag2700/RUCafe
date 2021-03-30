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

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

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
            salesTaxValue = 0.06625 * o.getSubtotal();
            salesTax.setText("" + df.format(salesTaxValue));
            totalValue = o.getSubtotal() + salesTaxValue;
            total.setText("" + df.format(totalValue));
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
        if (observableOrder.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
