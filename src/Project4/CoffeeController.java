package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;

public class CoffeeController {

    @FXML
    private RadioButton choiceShort, choiceTall, choiceGrande, choiceVenti;

    @FXML
    private ToggleGroup size;

    @FXML
    private MainMenuController mainMenuController;

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void addCoffee(ActionEvent event) {
        String newCoffeeSize = "";
        try {
            RadioButton selectedCoffeeSize = (RadioButton) size.getSelectedToggle();
            newCoffeeSize = selectedCoffeeSize.getText();
        }
        catch (NullPointerException e) {
            // make popup for no size selected?
            return;
        }

    }

    public void removeCoffee(ActionEvent event) {

    }
}
