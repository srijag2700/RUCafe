package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;


public class ViewOrderController {

    @FXML
    private MainMenuController mmController;

    @FXML
    private ListView currentOrderList;

    private ObservableList<MenuItem> observableOrder;

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    @FXML
    public void setOrder(Order o) {
        observableOrder = FXCollections.observableArrayList(o.getOrder());
        currentOrderList.setItems(observableOrder);
    }
}
