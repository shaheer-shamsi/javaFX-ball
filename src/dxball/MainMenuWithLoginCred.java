package dxball;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import static dxball.DxBall.theStage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.event.ActionEvent;

public class MainMenuWithLoginCred extends Application {
    private static final String CREDENTIALS_FILE = "credentials.txt";
    private Stage primaryStage;
    private Map<String, String> credentialsMap;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.credentialsMap = loadCredentialsFromFile(CREDENTIALS_FILE);

        primaryStage.setScene(createLoginScene());
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    private Scene createLoginScene() {

        AnchorPane root = new AnchorPane();
        root.setPrefSize(800, 800);


        AnchorPane loginForm = new AnchorPane();
        loginForm.setPrefSize(400, 400);
        loginForm.setStyle("-fx-background-color: #ffffff;");
        AnchorPane.setTopAnchor(loginForm, 200.0);
        AnchorPane.setLeftAnchor(loginForm, 200.0);


        TextField usernameField = createStyledTextField("User name", 75.0, 107.0);
        PasswordField passwordField = createStyledPasswordField("Password", 75.0, 189.0);
        Button loginButton = createStyledButton("Login", 137.0, 277.0);
        Button signupButton = createStyledButton("Sign Up", 137.0, 337.0);

        loginButton.setOnAction(event -> {
            if (validateLogin(usernameField.getText(), passwordField.getText())) {
                primaryStage.setScene(createMainMenuScene());
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid credentials", "Please enter valid username and password.");
            }
        });

        signupButton.setOnAction(event -> {
            primaryStage.setScene(createSignupScene());
        });


        loginForm.getChildren().addAll(usernameField, passwordField, loginButton, signupButton);


        Text titleText1 = new Text("JavaFX");
        titleText1.setFont(Font.font(40));
        titleText1.setFill(Color.WHITE);
        titleText1.setLayoutX(275);
        titleText1.setLayoutY(100);
        titleText1.setStrokeWidth(0);
        Text titleText2 = new Text("Ball");
        titleText2.setFont(Font.font(40));
        titleText2.setFill(Color.BLACK);
        titleText2.setLayoutX(405);
        titleText2.setLayoutY(100);
        titleText2.setStrokeWidth(0);


        root.getChildren().addAll(createStyledBackgroundPane(), loginForm, titleText1, titleText2);


        return new Scene(root);
    }

    private Scene createSignupScene() {

        AnchorPane root = new AnchorPane();
        root.setPrefSize(800, 800);


        AnchorPane signupForm = new AnchorPane();
        signupForm.setPrefSize(400, 400);
        signupForm.setStyle("-fx-background-color: #ffffff;");
        AnchorPane.setTopAnchor(signupForm, 200.0);
        AnchorPane.setLeftAnchor(signupForm, 200.0);


        TextField usernameField = createStyledTextField("User name", 75.0, 137.0);
        PasswordField passwordField = createStyledPasswordField("Password", 75.0, 219.0);
        Button signupButton = createStyledButton("Sign Up", 137.0, 307.0);

        signupButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter username and password.");
            } else if (credentialsMap.containsKey(username)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Username already exists. Please choose a different username.");
            } else {
                credentialsMap.put(username, password);
                saveCredentialsToFile(CREDENTIALS_FILE);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Signup successful. You can now login.");
                primaryStage.setScene(createLoginScene());
            }
        });


        signupForm.getChildren().addAll(usernameField, passwordField, signupButton);


        Text titleText1 = new Text("JavaFX");
        titleText1.setFont(Font.font(40));
        titleText1.setFill(Color.WHITE);
        titleText1.setLayoutX(275);
        titleText1.setLayoutY(100);
        titleText1.setStrokeWidth(0);
        Text titleText2 = new Text("Ball");
        titleText2.setFont(Font.font(40));
        titleText2.setFill(Color.BLACK);
        titleText2.setLayoutX(405);
        titleText2.setLayoutY(100);
        titleText2.setStrokeWidth(0);


        root.getChildren().addAll(createStyledBackgroundPane(), signupForm, titleText1, titleText2);


        return new Scene(root);
    }
    
    Button playButton,difficultyButton,exitButton;
    
    private Scene createMainMenuScene() {

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);


        Text menuText = new Text("Welcome to JavaFX Ball");
        menuText.setFont(Font.font(30));
        
        playButton = new Button("Play Game");
        difficultyButton = new Button("Difficulty++");
        exitButton = new Button("Exit Game");

        playButton.setOnAction(e -> buttonCode(e, "play"));
        //difficultyButton.setOnAction(e -> buttonCode(e, "difficulty"));
        exitButton.setOnAction(e -> buttonCode(e, "exit"));


        root.getChildren().addAll(menuText, playButton, difficultyButton, exitButton);


        return new Scene(root, 800, 800);
    }
    
    public void buttonCode(ActionEvent e, String buttonType) {
        if (buttonType.equals("play")) {
            theStage = primaryStage;
            IntroScene iScene = new IntroScene();
            Scene scene = iScene.getScene();
            primaryStage.setTitle("DxBall");
            primaryStage.setScene(scene);      
            primaryStage.show();
        } 
        /*else if (buttonType.equals("difficulty")) {
            // Create and set the difficulty scene
            DiffScene dScene = new DiffScene();
            Scene scene = dScene.getScene();
            theStage.setScene(scene);
            theStage.setTitle("JavaFX Ball - Difficulty");
            theStage.show();
            
        }*/
        if (buttonType.equals("exit")) {
            theStage.close();
        }
    }

    private Pane createStyledBackgroundPane() {
        AnchorPane backgroundPane = new AnchorPane();
        backgroundPane.setPrefSize(400, 800);
        backgroundPane.setStyle("-fx-background-color: #000000;");
        return backgroundPane;
    }

    private TextField createStyledTextField(String promptText, double layoutX, double layoutY) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 0px 0px 2px 0px;");
        textField.setFont(Font.font(20));
        textField.setLayoutX(layoutX);
        textField.setLayoutY(layoutY);
        return textField;
    }

    private PasswordField createStyledPasswordField(String promptText, double layoutX, double layoutY) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 0px 0px 2px 0px;");
        passwordField.setFont(Font.font(20));
        passwordField.setLayoutX(layoutX);
        passwordField.setLayoutY(layoutY);
        return passwordField;
    }

    private Button createStyledButton(String text, double layoutX, double layoutY) {
        Button button = new Button(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(130, 50);
        button.setStyle("-fx-background-color: #000000; -fx-font-size: 20px; -fx-text-fill: white;");
        return button;
    }

    private boolean validateLogin(String username, String password) {
        return credentialsMap.containsKey(username) && credentialsMap.get(username).equals(password);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Map<String, String> loadCredentialsFromFile(String filename) {
        Map<String, String> credentials = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    credentials.put(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return credentials;
    }

    private void saveCredentialsToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Map.Entry<String, String> entry : credentialsMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
