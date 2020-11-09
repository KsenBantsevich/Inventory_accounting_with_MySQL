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
import sample.database.Invoice;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

public class InvoiceListController {

    @FXML
    private TableView<Invoice> invoiceTableView;

    @FXML
    private TableColumn<Invoice,Integer> invoiceNumberColumn;

    @FXML
    private TableColumn<Invoice,Integer> formNumberColumn;

    @FXML
    private TableColumn<Invoice,Integer> inventoryNumberColumn;

    @FXML
    private TableColumn<Invoice,Integer> inventoryAmountColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private static Invoice selectedInvoice;

    private static ObservableList<Invoice> invoices;

    private void createInvoiceTable() {
        invoices = FXCollections.observableArrayList(Controller.getDatabase().getInvoiceList());
        invoiceTableView.setItems(invoices);
        invoiceNumberColumn.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceNumber"));
        formNumberColumn.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("formNumber"));
        inventoryNumberColumn.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("inventoryNumber"));
        inventoryAmountColumn.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("inventoryAmount"));

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createInvoiceTable();
        addButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/addInvoice.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                selectedInvoice = invoiceTableView.getSelectionModel().getSelectedItem();
                if (selectedInvoice != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/editInvoice.fxml"));
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
                selectedInvoice = invoiceTableView.getSelectionModel().getSelectedItem();
                if (selectedInvoice != null){
                    Controller.getDatabase().deleteInvoice(getSelectedInvoice());
                    invoices = FXCollections.observableArrayList(Controller.getDatabase().getInvoiceList());
                    invoiceTableView.setItems(invoices);
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

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Select your invoice");
        alert.showAndWait();
    }

    public static Invoice getSelectedInvoice() {
        return selectedInvoice;
    }
}
