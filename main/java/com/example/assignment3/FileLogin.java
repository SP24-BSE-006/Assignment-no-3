package com.example.assignment3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileLogin extends Application {

    private Map<String, String> userData = new HashMap<>();
    private Label messageLabel = new Label();

    @Override
    public void start(Stage primaryStage) {
        loadUserData();

        // Header Label
        Label headerLabel = new Label("Login Page");
        headerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");

        // Add image with styling
        Image image = new Image("file:C:\\Users\\DELL\\Desktop\\Assignment3\\src\\main\\resources\\image.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);  // Maintain aspect ratio
        imageView.setFitWidth(300);        // Dynamically resize image
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add a border

        // Button actions
        loginButton.setOnAction(e -> handleLogin(usernameField.getText().trim(), passwordField.getText().trim(), primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout for buttons
        HBox buttonBox = new HBox(15, loginButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Layout for form
        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.add(usernameLabel, 0, 0);
        formGrid.add(usernameField, 1, 0);
        formGrid.add(passwordLabel, 0, 1);
        formGrid.add(passwordField, 1, 1);
        formGrid.setAlignment(Pos.CENTER);

        // Main layout
        VBox mainLayout = new VBox(20, headerLabel, imageView, formGrid, buttonBox, messageLabel);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: lightblue; -fx-padding: 20px;");

        // Scene and Stage
        Scene scene = new Scene(mainLayout, 450, 500); // Adjusted window size
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin(String username, String password, Stage primaryStage) {
        if (userData.containsKey(username) && userData.get(username).equals(password)) {
            showSuccessWindow(username);
        } else {
            messageLabel.setText("Invalid name or password.");
        }
    }

    private void showSuccessWindow(String username) {
        Stage successStage = new Stage();
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        VBox layout = new VBox(20, welcomeLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 300, 150);
        successStage.setTitle("Login Successful");
        successStage.setScene(scene);
        successStage.show();
    }

    private void loadUserData() {
        File file = new File("users.txt");

        // Pre-fill file with user data if it doesn't exist
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("laiba:321\n");
                writer.write("aminah:654\n");
                writer.write("ayesha:987\n");
                writer.write("fatima:000\n");
                writer.write("minahil:123\n");
                writer.write("zunaira:456\n");
                writer.write("alishba:789\n");
                writer.write("tuba:mysis\n");
                writer.write("hira:lilsis\n");
                writer.write("arifa:bigsis\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userData.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
