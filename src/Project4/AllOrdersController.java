package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class AllOrdersController {

    @FXML
    private MainMenuController mmController;

    @FXML
    private ListView currentViewOrder;

    @FXML
    private ComboBox viewOrderNumber;

    @FXML
    private TextField currentOrderTotal;

    private static StoreOrders s;
    private DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    @FXML
    public void setAllStoreOrders(StoreOrders s) {
        this.s = s;
        ObservableList<Integer> allStoreOrderNumbers = FXCollections.observableArrayList();
        for (Order o : s.getStore()) {
            allStoreOrderNumbers.add(o.getOrderNumber());
        }

        viewOrderNumber.setItems(allStoreOrderNumbers);
        viewOrderNumber.getSelectionModel().selectFirst();

        ObservableList<MenuItem> currentOrderItems = FXCollections.observableArrayList();
        currentOrderItems.addAll(s.getOrderByOrderNumber((int) viewOrderNumber.getValue()).getOrder());
        currentViewOrder.setItems(currentOrderItems);
        currentOrderTotal.setText(df.format(s.getOrderByOrderNumber((int) viewOrderNumber.getValue()).getTotal()));

        viewOrderNumber.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null) {
                int orderNumber = (int) newVal;
                currentViewOrder.getItems().clear();
                currentOrderItems.clear();
                currentOrderItems.addAll(s.getOrderByOrderNumber(orderNumber).getOrder());
                currentViewOrder.setItems(currentOrderItems);
            }
        });

        currentViewOrder.getItems().addListener((ListChangeListener) change -> {
            currentOrderTotal.setText(df.format(s.getOrderByOrderNumber((int) viewOrderNumber.getValue()).getTotal()));
        });
    }
}
