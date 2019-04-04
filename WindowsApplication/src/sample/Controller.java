package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private PasswordField loginPass;

    @FXML
    private TextField loginUser;

    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login(ActionEvent event){
        String user = loginUser.getText();
        String pass = loginPass.getText();
        String search = user+","+pass;
        boolean found = false;

        BufferedReader bufferReader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/resources/users.csv");
            bufferReader = new BufferedReader(fileReader);
            String line;
            while((line=bufferReader.readLine()) !=null){
                if(line.equals(search)){
                    found = true;
                    break;
                }
            }
            if(found){
                System.out.println("User Found");
                pane = FXMLLoader.load(getClass().getResource("main.fxml"));
                pane.getChildren().setAll(pane);
            }
            else
                System.out.println("User Not Found");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
