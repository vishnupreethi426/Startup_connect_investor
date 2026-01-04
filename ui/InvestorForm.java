package ui;

import database.DatabaseOperations;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvestorForm {

    public static void show() {
        Stage stage = new Stage();
        stage.setTitle("Register Investor");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField categoryField = new TextField();
        TextField capacityField = new TextField();
        TextField revenueField = new TextField();

        grid.add(new Label("Investor ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Interested Category:"), 0, 2);
        grid.add(categoryField, 1, 2);
        grid.add(new Label("Investment Capacity:"), 0, 3);
        grid.add(capacityField, 1, 3);
        grid.add(new Label("Expected Revenue:"), 0, 4);
        grid.add(revenueField, 1, 4);

        Button submitBtn = new Button("Add Investor");
        submitBtn.setOnAction(e -> {
            try {
               // int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String category = categoryField.getText();
                double capacity = Double.parseDouble(capacityField.getText());
                double revenue = Double.parseDouble(revenueField.getText());

                database.DatabaseOperations.insertInvestor(new models.Investor(id, name, category, capacity, revenue));
                new Alert(Alert.AlertType.INFORMATION, "Investor Added Successfully").show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid input or error: " + ex.getMessage()).show();
            }
        });

        grid.add(submitBtn, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
