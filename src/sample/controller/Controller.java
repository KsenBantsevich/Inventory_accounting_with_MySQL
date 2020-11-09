package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    private static Database database;

    @FXML
    private Button inventoryOperationButton;

    @FXML
    private Button storageOperationButton;


    @FXML
    private Button invoiceOperationButton;

    @FXML
    private Button formButton;

    @FXML
   void initialize() throws SQLException, ClassNotFoundException {
        database = new Database();

        inventoryOperationButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/inventoryList.fxml"));
                Stage stage = (Stage) inventoryOperationButton.getScene().getWindow();
                stage.setTitle("Inventory");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        storageOperationButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/storageList.fxml"));
                Stage stage = (Stage) storageOperationButton.getScene().getWindow();
                stage.setTitle("Storages");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        invoiceOperationButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/invoiceList.fxml"));
                Stage stage = (Stage) invoiceOperationButton.getScene().getWindow();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        formButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/formList.fxml"));
                Stage stage = (Stage) formButton.getScene().getWindow();
                stage.setTitle("Forms");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static Database getDatabase() {
        return database;
    }
}
