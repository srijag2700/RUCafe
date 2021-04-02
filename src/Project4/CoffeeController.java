package Project4;

import java.util.ArrayList;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.text.DecimalFormat;

public class CoffeeController {

    @FXML
    private ToggleGroup size;

    @FXML
    private ListView selectedCoffee;

    @FXML
    private CheckBox cream, syrup, milk, caramel, whippedCream;

    @FXML
    private Spinner coffeeQuantity;

    @FXML
    private MainMenuController mmController;

    @FXML
    private TextField coffeeSubtotal;

    private DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    @FXML
    public void initialize() {
        selectedCoffee.getItems().addListener((ListChangeListener) change -> {
            double coffeeSum = 0;
            for (Object o : selectedCoffee.getItems()) {
                Coffee currentCoffee = (Coffee) o;
                currentCoffee.itemPrice();
                coffeeSum += currentCoffee.getPrice();
            }
            coffeeSubtotal.setText("" + df.format(coffeeSum));
        });
    }

    @FXML
    public void addCoffee(ActionEvent event) {
        String newCoffeeSize = "";
        try {
            RadioButton selectedCoffeeSize = (RadioButton) size.getSelectedToggle();
            newCoffeeSize = selectedCoffeeSize.getText();

            ArrayList<String> selectedAddIns = new ArrayList<>();
            if (cream.isSelected()) {
                selectedAddIns.add("Cream");
            }
            if (syrup.isSelected()) {
                selectedAddIns.add("Syrup");
            }
            if (milk.isSelected()) {
                selectedAddIns.add("Milk");
            }
            if (caramel.isSelected()) {
                selectedAddIns.add("Caramel");
            }
            if (whippedCream.isSelected()) {
                selectedAddIns.add("Whipped Cream");
            }

            Coffee newCoffee = new Coffee(newCoffeeSize, (Integer) coffeeQuantity.getValue(), selectedAddIns);
            selectedCoffee.getItems().add(newCoffee);
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Size Selected");
            alert.setHeaderText("Please select a size for your coffee.");
            alert.showAndWait();
        }
    }

    public void removeCoffee(ActionEvent event) {
        if (selectedCoffee.getSelectionModel().getSelectedItem() != null) {
            selectedCoffee.getItems().remove(selectedCoffee.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void addCoffeeToOrder(ActionEvent event) {
        if (selectedCoffee.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Coffee Selected");
            alert.setHeaderText("Please select coffee to add to your order.");
            alert.showAndWait();
        }
        else {
            for (Object o : selectedCoffee.getItems()) {
                Coffee currentCoffee = (Coffee) o;
                mmController.order.add(currentCoffee);
            }
            selectedCoffee.getItems().clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Coffee Added");
            alert.setHeaderText("Your coffee has been added to your order.");
            alert.showAndWait();
        }

    }
}
