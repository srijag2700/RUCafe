package Project4;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;

public class DonutsController {

    @FXML
    private ComboBox donutComboBox;

    @FXML
    private ListView yeastDonutFlavors, cakeDonutFlavors, donutHoleFlavors;

    @FXML
    private MainMenuController mainMenuController;

    @FXML
    private Spinner donutQuantity;

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }

    @FXML
    public void initialize() {
        donutComboBox.getItems().removeAll(donutComboBox.getItems());
        donutComboBox.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
        donutComboBox.getSelectionModel().select("Yeast Donut");

        cakeDonutFlavors.getItems().addAll("Matcha Green Tea", "Blueberry", "Cinnamon");
        yeastDonutFlavors.getItems().addAll("Glazed", "Boston Creme", "Strawberry Frosting");
        donutHoleFlavors.getItems().addAll("Powdered Sugar", "Chocolate Glazed", "Honey Dip");

        cakeDonutFlavors.setVisible(false);
        donutHoleFlavors.setVisible(false);
        yeastDonutFlavors.setVisible(true);
        //donutComboBox.getSelectionModel().select("Option B");

        donutComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null) {
                String donutType = (String) newVal;
                if (donutType.equals("Yeast Donut")) {
                    cakeDonutFlavors.setVisible(false);
                    donutHoleFlavors.setVisible(false);
                    yeastDonutFlavors.setVisible(true);
                }
                else if (donutType.equals("Cake Donut")) {
                    yeastDonutFlavors.setVisible(false);
                    donutHoleFlavors.setVisible(false);
                    cakeDonutFlavors.setVisible(true);
                }
                else if (donutType.equals("Donut Hole")) {
                    yeastDonutFlavors.setVisible(false);
                    cakeDonutFlavors.setVisible(false);
                    donutHoleFlavors.setVisible(true);
                }
            }
        });

    }
}
