package Project4;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CoffeeController {

    @FXML
    private RadioButton choiceShort, choiceTall, choiceGrande, choiceVenti;

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
            coffeeSubtotal.setText("" + coffeeSum);
        });
    }

    @FXML
    public void addCoffee(ActionEvent event) {
        System.out.println("adding"); // AAH
        String newCoffeeSize = "";
        try {
            RadioButton selectedCoffeeSize = (RadioButton) size.getSelectedToggle();
            newCoffeeSize = selectedCoffeeSize.getText();
            System.out.println("size is: " + newCoffeeSize); // also delete later!
        }
        catch (NullPointerException e) {
            // make popup for no size selected?
            System.out.println("help"); // delete later
            return;
        }

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

    public void removeCoffee(ActionEvent event) {
        if (selectedCoffee.getSelectionModel().getSelectedItem() != null) {
            selectedCoffee.getItems().remove(selectedCoffee.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void addCoffeeToOrder(ActionEvent event) {
        for (Object o : selectedCoffee.getItems()) {
            Coffee currentCoffee = (Coffee) o;
            mmController.order.add(currentCoffee);
            System.out.println("Coffee added.");
        }
        System.out.println(mmController.order.getOrder());
    }
}
