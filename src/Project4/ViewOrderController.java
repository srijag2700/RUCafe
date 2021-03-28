package Project4;

import javafx.fxml.FXML;

public class ViewOrderController {

    @FXML
    private MainMenuController mainMenuController;

    @FXML
    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }
}
