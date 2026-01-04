package ui;

import database.DatabaseOperations;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartupForm {

    public static void show() {
        Stage stage = new Stage();
        stage.setTitle("Register Startup");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField categoryField = new TextField();
        TextField locationField = new TextField();
        TextField investmentField = new TextField();
        TextField revenueField = new TextField();

        grid.add(new Label("Startup ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Category:"), 0, 2);
        grid.add(categoryField, 1, 2);
        grid.add(new Label("Location:"), 0, 3);
        grid.add(locationField, 1, 3);
        grid.add(new Label("Investment Needed:"), 0, 4);
        grid.add(investmentField, 1, 4);
        grid.add(new Label("Revenue:"), 0, 5);
        grid.add(revenueField, 1, 5);

        Button submitBtn = new Button("Add Startup");
        submitBtn.setOnAction(e -> {
            try {
               // int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String category = categoryField.getText();
                String location = locationField.getText();
                double investment = Double.parseDouble(investmentField.getText());
                double revenue = Double.parseDouble(revenueField.getText());

                database.DatabaseOperations.insertStartup(new models.Startup(id, name, category, location, investment, revenue));
                new Alert(Alert.AlertType.INFORMATION, "Startup Added Successfully").show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid input or error: " + ex.getMessage()).show();
            }
        });

        grid.add(submitBtn, 1, 6);

        Scene scene = new Scene(grid, 400, 350);
        stage.setScene(scene);
        stage.show();
    }
}

