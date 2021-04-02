package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

import javafx.event.ActionEvent;

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
            if (!viewOrderNumber.getItems().isEmpty()) {
                currentOrderTotal.setText(df.format(s.getOrderByOrderNumber((int) viewOrderNumber.getValue()).getTotal()));
            }
        });
    }

    @FXML
    public void cancelSelectOrder(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Order");
        alert.setHeaderText("You are about to cancel an order. This action is not reversible.");
        alert.setContentText("Are you sure you would like to cancel this order?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            if (viewOrderNumber.getSelectionModel().getSelectedItem() != null) {
                s.remove(s.getOrderByOrderNumber((int) viewOrderNumber.getValue()));
                viewOrderNumber.getItems().remove(viewOrderNumber.getSelectionModel().getSelectedItem());
            }

            if (viewOrderNumber.getItems().isEmpty()) {
                currentViewOrder.getItems().clear();
                currentOrderTotal.clear();
            }
        }
    }

    @FXML
    public void exportAllOrders(ActionEvent event) throws IOException {
        if (!s.getStore().isEmpty()) {
            try {
                s.exportAllOrders();
            }
            catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Orders Not Exported");
                alert.setHeaderText("There was an error exporting your orders. Please try again.");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Store Orders");
            alert.setHeaderText("Please add store orders before exporting.");
            alert.showAndWait();
        }
    }
}
