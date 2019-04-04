package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Image Identifier");
        primaryStage.setScene(new Scene(root, 676, 460));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void startServer() {

    }



    public static void main(String[] args) {
        launch(args);
    }
}
