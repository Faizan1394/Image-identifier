package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;


public class MainController implements Initializable {

    @FXML
    public volatile ImageView img;


    public void updateImage(){
        File file = new File("src\\resources\\readImage.png");
        try {

            BufferedImage bf = ImageIO.read(file);
            Image image0 = SwingFXUtils.toFXImage(bf,null);
            img.setImage(image0);
        }catch (Exception ex) {ex.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Server(this).start();
    }

    public void close(ActionEvent e) {
       System.exit(0);



    }

    public void logout(ActionEvent e) {
        try {
            Stage primaryStage = Main.primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.setScene(new Scene(root, 676, 460));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException exception) {System.err.println("Unhandled IOException\n" + exception);}
    }
}
