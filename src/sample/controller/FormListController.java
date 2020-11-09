package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Form;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class FormListController {
    @FXML
    private TableView<Form> formTableView;

    @FXML
    private TableColumn<Form, Integer> formNumberColumn;

    @FXML
    private TableColumn<Form, Integer> storageNumberColumn;

    @FXML
    private TableColumn<Form, Date> transactionDateColumn;

    @FXML
    private TableColumn<Form, String> employeeFioColumn;

    @FXML
    private TableColumn<Form, String> employeePostColumn;

    @FXML
    private Button inventorySearchButton;

    @FXML
    private Button invoiceSearchButton;

    @FXML
    private Button storageSearchButton;

    @FXML
    private Button cancelButton;

    private static ObservableList<Form> formList;

    private void createFormTable() {
        formList = FXCollections.observableArrayList(Controller.getDatabase().getFormList());
        formTableView.setItems(formList);
        formNumberColumn.setCellValueFactory(new PropertyValueFactory<Form, Integer>("formNumber"));
        storageNumberColumn.setCellValueFactory(new PropertyValueFactory<Form, Integer>("storageNumber"));
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<Form, Date>("transactionDate"));
        employeeFioColumn.setCellValueFactory(new PropertyValueFactory<Form,String >("employeeFio"));
        employeePostColumn.setCellValueFactory(new PropertyValueFactory<Form, String>("employeePost"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createFormTable();
        inventorySearchButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/inventorySearch.fxml"));
                Stage stage = (Stage) inventorySearchButton.getScene().getWindow();
                stage.setTitle("Search");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        invoiceSearchButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/invoiceSearch.fxml"));
                Stage stage = (Stage) invoiceSearchButton.getScene().getWindow();
                stage.setTitle("Search");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        storageSearchButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/storageSearch.fxml"));
                Stage stage = (Stage) storageSearchButton.getScene().getWindow();
                stage.setTitle("Search");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
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

}
