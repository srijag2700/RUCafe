package Project4;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DonutsController {

    @FXML
    private ComboBox donutComboBox;

    @FXML
    private MainMenuController mainMenuController;

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }

    @FXML
    public void initialize() {
        donutComboBox.getItems().removeAll(donutComboBox.getItems());
        donutComboBox.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
        //donutComboBox.getSelectionModel().select("Option B");
    }
}
