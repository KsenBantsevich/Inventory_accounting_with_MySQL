package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Storage;

import java.io.IOException;
import java.sql.SQLException;

public class AddStorageController {

    @FXML
    private TextField storageNumberTextField;

    @FXML
    private TextField storageNameTextField;

    @FXML
    private TextField storagePhoneTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        addButton.setOnAction(event -> {
            try {
                Storage storage = new Storage(storageNumberTextField.getText(),storageNameTextField.getText(),
                        storagePhoneTextField.getText());
                Controller.getDatabase().addStorage(storage);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/storageList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
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
                stage.setTitle("Storages");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

