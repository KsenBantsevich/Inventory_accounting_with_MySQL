package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Storage;

import java.io.IOException;
import java.sql.SQLException;

public class EditStorageController {

    @FXML
    private TextField storageNumberTextField;

    @FXML
    private TextField storageNameTextField;

    @FXML
    private TextField storagePhoneTextField;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        storageNumberTextField.setText(StorageListController.getSelectedStorage().getStorageNumber().toString());
        storageNameTextField.setText(StorageListController.getSelectedStorage().getStorageName());
        storagePhoneTextField.setText(StorageListController.getSelectedStorage().getStoragePhone().toString());
        editButton.setOnAction(event -> {
            try {
                Storage newStorage = new Storage(storageNumberTextField.getText(), storageNameTextField.getText(),storagePhoneTextField.getText());
                Controller.getDatabase().editStorage(StorageListController.getSelectedStorage(), newStorage);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/storageList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Storages");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/storageList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Учет инвентаря на складах предприятия");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
