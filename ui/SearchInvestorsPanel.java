package ui;

import database.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Investor;

import java.util.List;

public class SearchInvestorsPanel {

    public static VBox getPane() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TextField categoryField = new TextField();
        categoryField.setPromptText("Enter Category to Search");

        Button searchBtn = new Button("Search");
        TableView<Investor> table = new TableView<>();

        TableColumn<Investor, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Investor, String> nameCol = new TableColumn<>("Name");
        TableColumn<Investor, String> catCol = new TableColumn<>("Category");
        TableColumn<Investor, Double> capCol = new TableColumn<>("Capacity");
        TableColumn<Investor, Double> revCol = new TableColumn<>("Revenue");

        idCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().id).asObject());
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().name));
        catCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().interestedCategory));
        capCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().investmentCapacity).asObject());
        revCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().expectedRevenue).asObject());

        table.getColumns().addAll(idCol, nameCol, catCol, capCol, revCol);

        searchBtn.setOnAction(e -> {
            String category = categoryField.getText().trim();
            if (!category.isEmpty()) {
                List<Investor> list = DatabaseOperations.getInvestorsByCategory(category);
                table.setItems(FXCollections.observableArrayList(list));
            }
        });

        // Send Request section
        TextField startupIdField = new TextField();
        startupIdField.setPromptText("Enter Startup ID");
        TextField investorIdField = new TextField();
        investorIdField.setPromptText("Enter Investor ID");

        Button sendRequestBtn = new Button("Send Request");
        sendRequestBtn.setOnAction(e -> {
            try {
                int startupId = Integer.parseInt(startupIdField.getText());
                int investorId = Integer.parseInt(investorIdField.getText());
                DatabaseOperations.insertRequest(startupId, investorId, "Pending");
                new Alert(Alert.AlertType.INFORMATION, "Request Sent").show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid IDs or error").show();
            }
        });

        HBox requestBox = new HBox(10, startupIdField, investorIdField, sendRequestBtn);
        root.getChildren().addAll(categoryField, searchBtn, table, requestBox);

        return root;
    }
}
