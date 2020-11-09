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

public class InventorySearchController {

    @FXML
    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> storageNumberComboBox;

    @FXML
    private ComboBox<String> typeInventoryComboBox;


    @FXML
    private TableView<Inventory> resultTableView;



    @FXML
    private TableColumn<Inventory, String> typeInventoryColumn;

    @FXML
    private TableColumn<Inventory, String> nameInventoryColumn;

    @FXML
    private TableColumn<Inventory,Integer> inventoryAmountColumn;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> inventory = Controller.getDatabase().getInventoryType();
        typeInventoryComboBox.setItems(inventory);

        ObservableList<String> storage = Controller.getDatabase().getStorageNumber();
        storageNumberComboBox.setItems(storage);

      searchButton.setOnAction(event -> {
            ObservableList<Inventory> inventories = Controller.getDatabase().searchInventory(storageNumberComboBox.getValue(),
                    typeInventoryComboBox.getValue());
            resultTableView.setItems(inventories);


          inventoryAmountColumn.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("inventoryNumber"));
          typeInventoryColumn.setCellValueFactory(new PropertyValueFactory<Inventory, String>("typeInventory"));
          nameInventoryColumn.setCellValueFactory(new PropertyValueFactory<Inventory, String>("nameInventory"));


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

