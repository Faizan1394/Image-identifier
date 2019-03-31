package sample;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;

import java.io.File;

public class Main extends Application {

    Scene loginScene;
    Scene regScene;
    Scene imgScene;

    Button regloginButton;
    Button regregisterButton;

    // Firebase instance variables
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseUser mFirebaseUser;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Top Menu Bar
        Menu topMenu = new Menu("Menu");

        MenuItem loginMenuItem = new MenuItem("Login");
        topMenu.getItems().add(loginMenuItem);
        loginMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        loginMenuItem.setOnAction( e -> showloginPage(primaryStage, loginScene) );

        MenuItem regMenuItem = new MenuItem("Register");
        topMenu.getItems().add(regMenuItem);
        regMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        regMenuItem.setOnAction( e -> showregisterPage(primaryStage, regScene) );

        //
        // THIS BLOCK IS FOR TESTING PURPOSES ONLY, DELETE WHEN IT WORKS
        MenuItem imgMenuItem = new MenuItem("Image from Android");
        topMenu.getItems().add(imgMenuItem);
        imgMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        imgMenuItem.setOnAction( e -> showregisterPage(primaryStage, imgScene) );
        // END OF BLOCK
        //

        MenuItem exitMenuItem = new MenuItem("Exit");
        topMenu.getItems().add(exitMenuItem);
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        exitMenuItem.setOnAction( e -> System.exit(0));

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(topMenu);

        // LOGIN Screen

        //creating label email
        Text emailText = new Text("Email");

        //creating label password
        Text passwordText = new Text("Password");

        //Creating Text Filed for email
        TextField emailField = new TextField();

        //Creating Text for Register prompt
        Text regPrompt = new Text("Don't have an account?");

        //Creating Text Filed for password
        PasswordField passwordField = new PasswordField();

        //Creating Buttons
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(emailText, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(passwordText, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 1, 2);
        gridPane.add(regPrompt, 0, 10);
        gridPane.add(registerButton, 1, 10);


        // REGISTRATION Screen

        //creating label email
        Text regEmailText = new Text("Email");

        //creating label password
        Text regPasswordText = new Text("Password");

        //confirm password
        Text confirmPasswordText = new Text("Confirm Password");

        //Creating Text Filed for email
        TextField regemailField = new TextField();

        //Creating Text for Register prompt
        Text loginPrompt = new Text("Alreeady have an account?");

        //Creating Text Filed for password
        PasswordField regpasswordField = new PasswordField();

        //Creating Text Filed for password
        PasswordField confirmpasswordField = new PasswordField();

        //Creating Buttons
        regloginButton = new Button("Login");
        regregisterButton = new Button("Register");

        //Creating a Grid Pane
        GridPane regGridPane = new GridPane();

        //Setting size for the pane
        regGridPane.setMinSize(400, 200);

        //Setting the padding
        regGridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        regGridPane.setVgap(5);
        regGridPane.setHgap(5);

        //Setting the Grid alignment
        regGridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        regGridPane.add(regEmailText, 0, 0);
        regGridPane.add(regemailField, 1, 0);
        regGridPane.add(regPasswordText, 0, 1);
        regGridPane.add(regpasswordField, 1, 1);
        regGridPane.add(regregisterButton, 1, 2);
        regGridPane.add(loginPrompt, 0, 10);
        regGridPane.add(regloginButton, 1, 10);

        registerButton.setOnAction(e -> {
//            gridPane.getChildren().clear();
//            gridPane.add(emailText, 0, 0);
            showregisterPage(primaryStage, regScene);
        });

        regloginButton.setOnAction(e -> {
           showloginPage(primaryStage, loginScene);
        });

        StackPane loginPane = new StackPane(gridPane);

        //Setting size for the pane
//        loginPane.setMinSize(6000, 6000);
        loginPane.prefHeightProperty().bind(gridPane.widthProperty());
        loginPane.prefWidthProperty().bind(gridPane.heightProperty());


        //Setting the Grid alignment
        loginPane.setAlignment(Pos.CENTER);

        //Setting the padding
        loginPane.setPadding(new Insets(10, 10, 10, 10));

        StackPane regPane = new StackPane(regGridPane);

        //Setting size for the pane
//        regPane.setMinSize(6000, 6000);

        regPane.prefHeightProperty().bind(regPane.heightProperty());
        regPane.prefWidthProperty().bind(regPane.heightProperty());

        //Setting the padding
        regPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the Grid alignment
        regPane.setAlignment(Pos.CENTER);

        loginPane.setStyle("-fx-background-color: lightblue;");
        regPane.setStyle("-fx-background-color: lightblue;");

        // REGISTER Border Pane
        BorderPane bregPane = new BorderPane();
        bregPane.setTop(menuBar);
        bregPane.setCenter(loginPane);

        // LOGIN Border Pane
        BorderPane bloginPane = new BorderPane();
        bloginPane.setTop(menuBar);
        bloginPane.setCenter(regPane);

        // IMAGE Screen
        ImageView imageView = new ImageView();

        File file = new File("src/testing.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        StackPane imagePane = new StackPane(imageView);

        // Creating a scene object
        regScene = new Scene(bloginPane);

        loginScene = new Scene(bregPane);

        imgScene = new Scene(imagePane);

//        primaryStage.setScene(new Scene(root, 300, 275));

//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("https://console.firebase.google.com/project/itemidentifier-fb57d/database/itemidentifier-fb57d/data");

//        System.out.println(ref);

//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        showloginPage(primaryStage, loginScene);

        primaryStage.show();


    }

    public static void showregisterPage(Stage primaryStage, Scene regScene) {

        primaryStage.setTitle("Registration - Item Identifier");
        primaryStage.setScene(regScene);
    }

    public static void showimagePage(Stage primaryStage, Scene imgScene) {

        primaryStage.setTitle("Image from Android - Item Identifier");
        primaryStage.setScene(imgScene);
    }

    public static void showloginPage(Stage primaryStage, Scene loginScene) {

        primaryStage.setTitle("Login - Item Identifier");
        primaryStage.setScene(loginScene);
    }

    public static boolean passwordMatch() {
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
