package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dashboard {

    public static void show(Stage primaryStage) {
        primaryStage.setTitle("StartupConnect Dashboard");

        // Header
        Text header = new Text("StartupConnect");
        header.setFont(Font.font("Arial", 36));
        header.setFill(Color.DARKBLUE);
        header.setEffect(new DropShadow(3, Color.GRAY));

        // Buttons
        Button startupBtn = new Button("Register Startup");
        Button investorBtn = new Button("Register Investor");
        Button searchInvestorsBtn = new Button("Search Investors");
        Button requestsBtn = new Button("View Requests");

        // Style buttons
        Button[] buttons = {startupBtn, investorBtn, searchInvestorsBtn, requestsBtn};
        for (Button btn : buttons) {
            btn.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-font-size: 16px;");
            btn.setPrefWidth(200);
            btn.setPrefHeight(50);
            btn.setEffect(new DropShadow(5, Color.GRAY));
        }

        // Button Actions
        startupBtn.setOnAction(e -> StartupForm.show());
        investorBtn.setOnAction(e -> InvestorForm.show());

        searchInvestorsBtn.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Search Investors");
            stage.setScene(new Scene(SearchInvestorsPanel.getPane(), 700, 500));
            stage.show();
        });

        requestsBtn.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Requests Panel");
            stage.setScene(new Scene(RequestPanel.getPane(), 600, 400));
            stage.show();
        });

        // Layout
        VBox root = new VBox(30);
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(header);

        HBox buttonBox = new HBox(20, startupBtn, investorBtn, searchInvestorsBtn, requestsBtn);
        buttonBox.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonBox);

        // Scene
        Scene scene = new Scene(root, 900, 400);
        scene.setFill(Color.LIGHTGRAY);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
