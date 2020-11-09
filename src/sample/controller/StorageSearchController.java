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
import java.sql.SQLException;

public class StorageSearchController {
    @FXML

    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TableView<Storage> resultTableView;

    @FXML
    private TableColumn<Storage, String> storageNumberTableColumn;

    @FXML
    private TableColumn<Storage, String> storageNameTableColumn;

    @FXML
    private TableColumn<Storage, String> storagePhoneTableColumn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        searchButton.setOnAction(event -> {
            ObservableList<Storage> storages = Controller.getDatabase().searchStorage();
            resultTableView.setItems(storages);
            storageNumberTableColumn.setCellValueFactory(new PropertyValueFactory<Storage, String>("storageNumber"));
            storageNameTableColumn.setCellValueFactory(new PropertyValueFactory<Storage, String>("storageName"));
            storagePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Storage, String>("storagePhone"));
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
