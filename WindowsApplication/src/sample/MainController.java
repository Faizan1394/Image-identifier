package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void close(ActionEvent e) {
        //TODO
        // Close Socket

        System.exit(0);
    }

    public void logout(ActionEvent e) {
        //TODO
        // Close Socket

        try {
            Stage primaryStage = Main.primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.setScene(new Scene(root, 676, 460));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception IOException) {System.err.println("Unhandled IOException");}
    }
}
