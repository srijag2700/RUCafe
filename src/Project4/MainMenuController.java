package Project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
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
    }

    @FXML
    public void openCoffeeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Order Coffee");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void openViewOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewOrder.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("View Order");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void openAllOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("allOrders.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("All Orders");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
