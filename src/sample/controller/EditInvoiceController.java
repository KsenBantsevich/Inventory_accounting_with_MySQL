package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Invoice;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class EditInvoiceController {


    @FXML
    private TextField invoiceNumberTextField;

    @FXML
    private TextField formNumberTextField;

    @FXML
    private TextField inventoryNumberTextField;

    @FXML
    private TextField inventoryAmountTextField;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        invoiceNumberTextField.setText(InvoiceListController.getSelectedInvoice().getInvoiceNumber().toString());
        formNumberTextField.setText(InvoiceListController.getSelectedInvoice().getFormNumber().toString());
        inventoryNumberTextField.setText(InvoiceListController.getSelectedInvoice().getInventoryNumber().toString());
        inventoryAmountTextField.setText(InvoiceListController.getSelectedInvoice().getInventoryAmount().toString());


        editButton.setOnAction(event -> {
            try {
                Invoice newInvoice = new Invoice(Integer.valueOf(invoiceNumberTextField.getText()),
                        Integer.valueOf(formNumberTextField.getText()),
                        Integer.valueOf(inventoryNumberTextField.getText()),
                        Integer.valueOf(inventoryAmountTextField.getText()));
                Controller.getDatabase().editInvoice(InvoiceListController.getSelectedInvoice(), newInvoice);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/invoiceList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/invoiceList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
