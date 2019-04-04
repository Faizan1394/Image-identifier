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
    private Server server;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        server = new Server();
    }

    public void close(ActionEvent e) {
        try {
            server.socket.close();
        } catch (IOException exception ) {System.err.println("Unhandled IOException\n" + exception);}

            System.exit(0);
    }

    public void logout(ActionEvent e) {
        try {
            server.socket.close();

            Stage primaryStage = Main.primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.setScene(new Scene(root, 676, 460));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException exception) {System.err.println("Unhandled IOException\n" + exception);}
    }
}
