package ui;

import database.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Request;

import java.util.List;

public class RequestPanel {

    public static VBox getPane() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TableView<Request> table = new TableView<>();
        TableColumn<Request, Integer> startupCol = new TableColumn<>("Startup ID");
        TableColumn<Request, Integer> investorCol = new TableColumn<>("Investor ID");
        TableColumn<Request, String> statusCol = new TableColumn<>("Status");

        startupCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().startupId).asObject());
        investorCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().investorId).asObject());
        statusCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().status));

        table.getColumns().addAll(startupCol, investorCol, statusCol);

        Button loadBtn = new Button("Load Pending Requests");
        Button acceptBtn = new Button("Accept");
        Button rejectBtn = new Button("Reject");

        loadBtn.setOnAction(e -> {
            List<Request> list = DatabaseOperations.getPendingRequests();
            table.setItems(FXCollections.observableArrayList(list));
        });

        acceptBtn.setOnAction(e -> {
            Request req = table.getSelectionModel().getSelectedItem();
            if (req != null) {
                DatabaseOperations.updateRequestStatus(req.startupId, req.investorId, "Accepted");
                new Alert(Alert.AlertType.INFORMATION, "Request Accepted").show();
            }
        });

        rejectBtn.setOnAction(e -> {
            Request req = table.getSelectionModel().getSelectedItem();
            if (req != null) {
                DatabaseOperations.updateRequestStatus(req.startupId, req.investorId, "Rejected");
                new Alert(Alert.AlertType.WARNING, "Request Rejected").show();
            }
        });

        HBox buttons = new HBox(10, loadBtn, acceptBtn, rejectBtn);
        root.getChildren().addAll(table, buttons);
        return root;
    }
}
