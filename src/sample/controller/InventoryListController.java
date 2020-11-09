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
import sample.database.Inventory;

import java.io.IOException;
import java.sql.SQLException;

public class InventoryListController {

    @FXML
    private TableView<Inventory> inventoryTableView;

    @FXML
    private TableColumn<Inventory, Integer> inventoryNumberColumn;

    @FXML
    private TableColumn<Inventory, String> nameInventoryColumn;

    @FXML
    private TableColumn<Inventory, String> typeInventoryColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private static Inventory selectedInventory;

    private static ObservableList<Inventory> inventories;

    private void createInventoryTable() {
        inventories = FXCollections.observableArrayList(Controller.getDatabase().getInventoryList());
        inventoryTableView.setItems(inventories);
        inventoryNumberColumn.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("inventoryNumber"));
        nameInventoryColumn.setCellValueFactory(new PropertyValueFactory<Inventory, String>("nameInventory"));
        typeInventoryColumn.setCellValueFactory(new PropertyValueFactory<Inventory, String>("typeInventory"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createInventoryTable();
        addButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/addInventory.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                selectedInventory = inventoryTableView.getSelectionModel().getSelectedItem();
                if (selectedInventory != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/editInventory.fxml"));
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
                selectedInventory = inventoryTableView.getSelectionModel().getSelectedItem();
                if (selectedInventory != null){
                    Controller.getDatabase().deleteInventory(getSelectedInventory());
                    inventories = FXCollections.observableArrayList(Controller.getDatabase().getInventoryList());
                    inventoryTableView.setItems(inventories);
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
                stage1.setTitle("Учет инвентаря на складах предприятия");
                stage1.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Inventory getSelectedInventory(){
        return selectedInventory;
    }

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Select your inventory");
        alert.showAndWait();
    }
}