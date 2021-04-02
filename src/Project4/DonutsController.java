package Project4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

/**
 * This class represents the controller for the Order Donuts menu.
 * It controls the functions of picking a donut type, picking a donut flavor, choosing a quantity, adding and removing donuts, and adding donuts to the current order.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class DonutsController {

    private DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    @FXML
    private ComboBox donutComboBox;

    @FXML
    private ListView yeastDonutFlavors, cakeDonutFlavors, donutHoleFlavors, selectedDonuts;

    @FXML
    private MainMenuController mmController;

    @FXML
    private Spinner donutQuantity;

    @FXML
    private TextField donutSubtotal;

    /**
     * Connects the Main Menu controller to this controller so that donuts can be added to the order.
     * @param controller the Main Menu controller associated with this controller
     */
    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mmController = controller;
    }

    /**
     * Initializes the controller with the types & flavors of donuts, the relationships between each donut type and its flavors, and the running total value of the added donuts.
     */
    @FXML
    public void initialize() {
        donutComboBox.getItems().removeAll(donutComboBox.getItems());
        donutComboBox.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
        //donutComboBox.getSelectionModel().select("Yeast Donut");

        ObservableList<String> cakeFlavors = FXCollections.observableArrayList("Matcha Green Tea", "Blueberry", "Cinnamon");
        ObservableList<String> yeastFlavors = FXCollections.observableArrayList("Glazed", "Boston Creme", "Strawberry Frosting");
        ObservableList<String> holeFlavors = FXCollections.observableArrayList("Powdered Sugar", "Chocolate Glazed", "Honey Dip");

        donutComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null) {
                cakeDonutFlavors.setItems(cakeFlavors);
                yeastDonutFlavors.setItems(yeastFlavors);
                donutHoleFlavors.setItems(holeFlavors);

                String donutType = (String) newVal;
                if (donutType.equals("Yeast Donut")) {
                    cakeDonutFlavors.getSelectionModel().clearSelection();
                    cakeDonutFlavors.setVisible(false);
                    donutHoleFlavors.getSelectionModel().clearSelection();
                    donutHoleFlavors.setVisible(false);
                    yeastDonutFlavors.setVisible(true);
                }
                else if (donutType.equals("Cake Donut")) {
                    yeastDonutFlavors.getSelectionModel().clearSelection();
                    yeastDonutFlavors.setVisible(false);
                    donutHoleFlavors.getSelectionModel().clearSelection();
                    donutHoleFlavors.setVisible(false);
                    cakeDonutFlavors.setVisible(true);
                }
                else if (donutType.equals("Donut Hole")) {
                    yeastDonutFlavors.getSelectionModel().clearSelection();
                    yeastDonutFlavors.setVisible(false);
                    cakeDonutFlavors.getSelectionModel().clearSelection();
                    cakeDonutFlavors.setVisible(false);
                    donutHoleFlavors.setVisible(true);
                }
            }
        });

        selectedDonuts.getItems().addListener((ListChangeListener) change -> {
            double donutSum = 0;
            for (Object o : selectedDonuts.getItems()) {
                Donut currentDonut = (Donut) o;
                currentDonut.itemPrice();
                donutSum += currentDonut.getPrice();
            }
            donutSubtotal.setText("" + df.format(donutSum));
        });

    }

    /**
     * Adds the configured donut to the list of selected donuts.
     * @param event the ">>" button getting clicked
     */
    @FXML
    public void addDonut(ActionEvent event) {
        String selectedDonutType = "";
        if (yeastDonutFlavors.isVisible()) {
            selectedDonutType = (String) yeastDonutFlavors.getSelectionModel().getSelectedItem();
        }
        else if (cakeDonutFlavors.isVisible()) {
            selectedDonutType = (String) cakeDonutFlavors.getSelectionModel().getSelectedItem();
        }
        else if (donutHoleFlavors.isVisible()) {
            selectedDonutType = (String) donutHoleFlavors.getSelectionModel().getSelectedItem();
        }

        if (selectedDonutType != null) {
            Donut newDonut = new Donut((String) donutComboBox.getSelectionModel().getSelectedItem(), selectedDonutType, (Integer) donutQuantity.getValue());
            selectedDonuts.getItems().add(newDonut);
        }
    }

    /**
     * Removes the selected donut from the list of selected donuts.
     * @param event the "<<" button getting clicked
     */
    @FXML
    public void removeDonut(ActionEvent event) {
        if (selectedDonuts.getSelectionModel().getSelectedItem() != null) {
            selectedDonuts.getItems().remove(selectedDonuts.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Adds the selected donuts to the overall order.
     * @param event the "Add To Order" button getting clicked
     */
    @FXML
    public void addDonutsToOrder(ActionEvent event) {
        if (selectedDonuts.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Donuts Selected");
            alert.setHeaderText("Please select donuts to add to your order.");
            alert.showAndWait();
        }

        else {
            for (Object o : selectedDonuts.getItems()) {
                Donut currentDonut = (Donut) o;
                mmController.order.add(currentDonut);
            }
            selectedDonuts.getItems().clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donuts Added");
            alert.setHeaderText("Your donuts have been added to your order.");
            alert.showAndWait();
        }
    }
}
