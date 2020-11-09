package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Inventory;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddInventoryController {

    @FXML
    private TextField inventoryNumberTextField;

    @FXML
    private TextField nameInventoryTextField;

    @FXML
    private TextField typeInventoryTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        addButton.setOnAction(event -> {
            try {
                Inventory inventory = new Inventory(Integer.valueOf(inventoryNumberTextField.getText()),nameInventoryTextField.getText(),
                        typeInventoryTextField.getText());

                Controller.getDatabase().addInventory(inventory);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/inventoryList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Inventory");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/inventoryList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Inventory");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

