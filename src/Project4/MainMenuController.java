package Project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    protected StoreOrders allStoreOrders = new StoreOrders();
    protected Order order = new Order();

    @FXML
    private Button orderCoffee, orderDonuts, viewOrder, allOrders;

    @FXML
    public void openDonutOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("donuts.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Order Donuts");
        stage.setScene(new Scene(root));
        stage.show();

        DonutsController dController = loader.getController();
        dController.setMainMenuController(this);
    }

    @FXML
    public void openCoffeeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Order Coffee");
        stage.setScene(new Scene(root));
        stage.show();

        CoffeeController cController = loader.getController();
        cController.setMainMenuController(this);
    }

    @FXML
    public void openViewOrder(ActionEvent event) throws IOException {
        if (order.getOrder().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Order");
            alert.setHeaderText("Your current order is empty.");
            alert.setContentText("Please add items to your order.");
            alert.showAndWait();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewOrder.fxml"));
            Parent root = (Parent) loader.load();

            Stage stage = new Stage();
            stage.setTitle("View Order");
            stage.setScene(new Scene(root));
            stage.show();

            ViewOrderController vController = loader.getController();
            vController.setMainMenuController(this);
            vController.setOrder(order);
            vController.setAllStoreOrders(allStoreOrders);
        }
    }

    @FXML
    public void openAllOrders(ActionEvent event) throws IOException {
        if (allStoreOrders.getStore().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Store Orders");
            alert.setHeaderText("You have no store orders.");
            alert.setContentText("Please add orders to your store.");
            alert.showAndWait();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allOrders.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("All Orders");
            stage.setScene(new Scene(root));
            stage.show();

            AllOrdersController aController = loader.getController();
            aController.setMainMenuController(this);
            aController.setAllStoreOrders(allStoreOrders);
        }
    }
}
