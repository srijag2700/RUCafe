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

/**
 * This class represents the controller for the Order Coffee menu.
 * It controls the functions of selecting a size and add ons for coffee, choosing a quantity, adding and removing coffee, and adding coffee to the current order.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

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

    /**
     * Connects the Main Menu controller to this controller so that coffee can be added to the order.
     * @param controller the Main Menu controller associated with this controller
     */
    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    /**
     * Initializes the controller with the running total value of the added coffee.
     */
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

    /**
     * Adds the configured coffee to the list of selected coffees.
     * @param event the "add coffee" button getting clicked
     */
    @FXML
    public void addCoffee(ActionEvent event) {
        String newCoffeeSize = "";
        try {
            RadioButton selectedCoffeeSize = (RadioButton) size.getSelectedToggle();
            newCoffeeSize = selectedCoffeeSize.getText();

            ArrayList<String> selectedAddIns = new ArrayList<>();
            Coffee newCoffee = new Coffee(newCoffeeSize, (Integer) coffeeQuantity.getValue(), selectedAddIns);
            if (cream.isSelected()) {
                newCoffee.add("Cream");
            }
            if (syrup.isSelected()) {
                newCoffee.add("Syrup");
            }
            if (milk.isSelected()) {
                newCoffee.add("Milk");
            }
            if (caramel.isSelected()) {
                newCoffee.add("Caramel");
            }
            if (whippedCream.isSelected()) {
                newCoffee.add("Whipped Cream");
            }

            selectedCoffee.getItems().add(newCoffee);
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Size Selected");
            alert.setHeaderText("Please select a size for your coffee.");
            alert.showAndWait();
        }
    }

    /**
     * Removes the selected coffee from the list of selected coffees.
     * @param event the "remove coffee" button getting clicked
     */
    public void removeCoffee(ActionEvent event) {
        if (selectedCoffee.getSelectionModel().getSelectedItem() != null) {
            selectedCoffee.getItems().remove(selectedCoffee.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Adds the selected coffees to the overall order.
     * @param event the "Add To Order" button getting clicked
     */
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
