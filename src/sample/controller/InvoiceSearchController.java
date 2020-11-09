package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.database.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class InvoiceSearchController {
    @FXML

    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> nameInventoryComboBox;

    @FXML
    private TableView<Search> resultTableView;

    @FXML
    private TableColumn<Search, String> nameInventoryColumn;

    @FXML
    private TableColumn<Search, Date> transactionDateColumn;

    @FXML
    private TableColumn<Search, Integer> inventoryAmountColumn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> inventory = Controller.getDatabase().getInventoryName();
        nameInventoryComboBox.setItems(inventory);

        searchButton.setOnAction(event -> {
            ObservableList<Search> search = Controller.getDatabase().searchInvoice(nameInventoryComboBox.getValue());
            resultTableView.setItems(search);
            nameInventoryColumn.setCellValueFactory(new PropertyValueFactory<Search, String>("nameInventory"));
            transactionDateColumn.setCellValueFactory(new PropertyValueFactory<Search, Date>("transactionDate"));
            inventoryAmountColumn.setCellValueFactory(new PropertyValueFactory<Search, Integer>("inventoryAmount"));
        });

        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/formList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Forms");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
