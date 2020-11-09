package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Storage;

import java.io.IOException;
import java.sql.SQLException;

public class StorageListController {

    @FXML
    private TableView<Storage> storageTableView;

    @FXML
    private TableColumn<Storage, Integer> storageNumberColumn;

    @FXML
    private TableColumn<Storage, String> storageNameColumn;

    @FXML
    private TableColumn<Storage, Integer> storagePhoneColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private static Storage selectedStorage;

    private static ObservableList<Storage> storages;

    private void createStorageTable() {
        storages = FXCollections.observableArrayList(Controller.getDatabase().getStorageList());
        storageTableView.setItems(storages);
        storageNumberColumn.setCellValueFactory(new PropertyValueFactory<Storage, Integer>("storageNumber"));
        storageNameColumn.setCellValueFactory(new PropertyValueFactory<Storage, String>("storageName"));
        storagePhoneColumn.setCellValueFactory(new PropertyValueFactory<Storage, Integer>("storagePhone"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createStorageTable();
        addButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/addStorage.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                selectedStorage = storageTableView.getSelectionModel().getSelectedItem();
                if (selectedStorage != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/editStorage.fxml"));
                    Stage stage = (Stage) editButton.getScene().getWindow();
                    stage.setTitle("Edit");
                    stage.setScene(new Scene(root, 650, 350));
                } else {
                    showError();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                selectedStorage = storageTableView.getSelectionModel().getSelectedItem();
                if (selectedStorage != null){
                    Controller.getDatabase().deleteStorage(getSelectedStorage());
                    storages = FXCollections.observableArrayList(Controller.getDatabase().getStorageList());
                    storageTableView.setItems(storages);
                } else {
                    showError();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/sample.fxml"));
                Stage stage1 = (Stage) cancelButton.getScene().getWindow();
                stage1.setTitle("Учёт инвентаря на складах предприятия");
                stage1.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Storage getSelectedStorage(){
        return selectedStorage;
    }

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Select your storage");
        alert.showAndWait();
    }
}