package Project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class is used to set the stage and run the RUOrder Main Menu GUI.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Main extends Application {

    /**
     * Method that starts the Main Menu GUI.
     * @param primaryStage the stage of the application
     * @throws Exception if there are runtime errors
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        primaryStage.setTitle("RU Cafe");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    /**
     * Main method that launches the application.
     * @param args the given arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
