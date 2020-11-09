package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/second_lab?useUnicode=true&serverTimezone=Europe/Moscow";
    private static final String user = "root";
    private static final String password = "KsenBantsevich";

    private static Connection con;
    private static Statement stmt;


    private List<Inventory> inventoryList = new ArrayList<>();
    private List<Storage> storageList = new ArrayList<>();
    private List<Invoice> invoiceList = new ArrayList<>();
    private List<Form> formList = new ArrayList<>();

    public Database() throws SQLException, ClassNotFoundException {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt =  con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        setInventory();
        setStorage();
        setInvoice();
        setForm();
    }


    public void setInventory() throws SQLException {
        Integer inventoryNumber;
        String nameInventory;
        String typeInventory;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM inventory");
            while (resultSet.next()) {
                inventoryNumber = resultSet.getInt(1);
                nameInventory = resultSet.getString(2);
                typeInventory = resultSet.getString(3);
                inventoryList.add(new Inventory(inventoryNumber,nameInventory,typeInventory));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void setStorage() throws SQLException {
         String storageNumber;
         String storageName;
         String storagePhone;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM storage");
            while (resultSet.next()) {
                storageNumber = resultSet.getString(1);
                storageName = resultSet.getString(2);
                storagePhone = resultSet.getString(3);
                storageList.add(new Storage(storageNumber,storageName, storagePhone));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

   public void setInvoice() throws SQLException {
        Integer invoiceNumber;
        Integer formNumber;
        Integer inventoryNumber;
        Integer inventoryAmount;

        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM invoice");
            while (resultSet.next()) {
                invoiceNumber = resultSet.getInt(1);
                formNumber = resultSet.getInt(2);
                inventoryNumber = resultSet.getInt(3);
                inventoryAmount = resultSet.getInt(4);
                invoiceList.add(new Invoice(invoiceNumber, formNumber, inventoryNumber, inventoryAmount));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

  public void setForm() throws SQLException {
      Integer formNumber;
      Integer storageNumber;
      Date transactionDate;
      String employeeFio;
      String employeePost;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM form");
            while (resultSet.next()) {
                formNumber = resultSet.getInt(1);
                storageNumber = resultSet.getInt(2);
                transactionDate = resultSet.getDate(3);
                employeeFio = resultSet.getString(4);
                employeePost = resultSet.getString(5);

                formList.add(new Form(formNumber, storageNumber, transactionDate, employeeFio, employeePost));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void addInventory(Inventory inventory) throws SQLException {
        try {
            String sql = "INSERT INTO inventory (inventoryNumber, nameInventory, typeInventory) Values (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, inventory.getInventoryNumber());
            preparedStatement.setString(2, inventory.getNameInventory());
            preparedStatement.setString(3, inventory.getTypeInventory());
            inventoryList.add(inventory);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

   public void addStorage(Storage storage) throws SQLException {
        try {
            String sql = "INSERT INTO storage (storageNumber, storageName, storagePhone) Values (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storage.getStorageNumber());
            preparedStatement.setString(2, storage.getStorageName());
            preparedStatement.setString(3, storage.getStoragePhone());
            storageList.add(storage);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

     public void addInvoice(Invoice invoice) throws SQLException {
        try {
            String sql = "INSERT INTO invoice (invoiceNumber, formNumber, inventoryNumber, inventoryAmount) Values (?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, invoice.getInvoiceNumber());
            preparedStatement.setInt(2, invoice.getFormNumber());
            preparedStatement.setInt(3, invoice.getInventoryNumber());
            preparedStatement.setInt(4, invoice.getInventoryAmount());
            invoiceList.add(invoice);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void deleteInventory(Inventory inventory) throws SQLException {
        try {
            String sql = "DELETE FROM inventory WHERE inventoryNumber =" + inventory.getInventoryNumber();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            inventoryList.remove(inventory);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deleteStorage(Storage storage) throws SQLException {
        try {
            String sql = "DELETE FROM storage WHERE storageNumber =" + storage.getStorageNumber();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            storageList.remove(storage);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deleteInvoice(Invoice invoice) throws SQLException {
        try {
            String sql = "DELETE FROM invoice WHERE invoiceNumber =" + invoice.getInvoiceNumber();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            invoiceList.remove(invoice);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


public void editInventory(Inventory inventory, Inventory newInventory) throws SQLException {
    try {
        String sql = "UPDATE inventory SET inventoryNumber = ?, nameInventory = ?, typeInventory = ?" +
                "WHERE inventoryNumber = " + newInventory.getInventoryNumber();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, newInventory.getInventoryNumber());
        preparedStatement.setString(2, newInventory.getNameInventory());
        preparedStatement.setString(3, newInventory.getTypeInventory());
        for(int i = 0; i < inventoryList.size(); i++){
            if(inventoryList.get(i).equals(inventory)) {
                inventoryList.set(i, newInventory);
                break;
            }
        preparedStatement.executeUpdate();
        preparedStatement.close();

        }

    } catch (SQLException sqlEX){
        sqlEX.printStackTrace();
    }
}

    public void editStorage(Storage storage, Storage newStorage) throws SQLException {
        try {
            String sql = "UPDATE storage SET storageNumber = ?, storageName = ?, storagePhone = ?" +
                    "WHERE storageNumber = " + newStorage.getStorageNumber();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, newStorage.getStorageNumber());
            preparedStatement.setString(2, newStorage.getStorageName());
            preparedStatement.setString(3, newStorage.getStoragePhone());

            for (int i = 0; i < storageList.size(); i++) {
                if (storageList.get(i).equals(storage)) {
                    storageList.set(i, newStorage);
                    break;
                }
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } catch (SQLException sqlEX) {
            sqlEX.printStackTrace();
        }
    }

    public void editInvoice(Invoice invoice, Invoice newInvoice) throws SQLException {
        try {
            String sql = "UPDATE invoice SET invoiceNumber = ?, formNumber = ?,inventoryNumber = ?, inventoryAmount = ?" +
                    "WHERE invoiceNumber = " + newInvoice.getInvoiceNumber();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, newInvoice.getInventoryNumber());
            preparedStatement.setInt(2, newInvoice.getFormNumber());
            preparedStatement.setInt(3, newInvoice.getInventoryNumber());
            preparedStatement.setInt(4, newInvoice.getInventoryAmount());

            for (int i = 0; i < invoiceList.size(); i++) {
                if (invoiceList.get(i).equals(invoice)) {
                    invoiceList.set(i, newInvoice);
                    break;
                }
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } catch (SQLException sqlEX) {
            sqlEX.printStackTrace();
        }
    }

   public ObservableList<String> getInventoryType() {
        ObservableList<String> inventory = FXCollections.observableArrayList();
        try {
           ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT typeInventory FROM inventory");
            while (resultSet.next()) {
                inventory.add(resultSet.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return inventory;
    }

    public ObservableList<String> getStorageNumber() {
        ObservableList<String> storage = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT storageNumber FROM storage");
            while (resultSet.next()) {
                storage.add(resultSet.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return storage;
    }

    public ObservableList<Inventory> searchInventory(String storageNumber, String typeInventory) {
        ObservableList<Inventory> search = FXCollections.observableArrayList();
        PreparedStatement findingStatement = null;
        try {
            findingStatement= con.prepareStatement
                    ("select  sum(invoice.inventoryAmount), inventory.nameInventory, inventory.typeInventory from invoice\n" +
                            "join inventory on inventory.inventoryNumber = invoice.inventoryNumber\n" +
                            "join form on form.formNumber = invoice.formNumber\n" +
                            "where form.storageNumber = ? and inventory.typeInventory = ?\n" +
                            "group by invoice.inventoryNumber\n" +
                            "having sum(invoice.inventoryAmount);");
            findingStatement.setString(1, storageNumber);
            findingStatement.setString(2, typeInventory);

            ResultSet resultSet = findingStatement.executeQuery();

            while (resultSet.next()) {
                search.add(new Inventory(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));


            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return search;
    }

    public ObservableList<String> getInventoryName() {
        ObservableList<String> inventory = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT nameInventory FROM inventory");
            while (resultSet.next()) {
                inventory.add(resultSet.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return inventory;
    }

    public ObservableList<Search> searchInvoice(String nameInventory) {
        ObservableList<Search> search = FXCollections.observableArrayList();
        PreparedStatement findingStatement = null;
        try {
            findingStatement= con.prepareStatement
                    ("select inventory.nameInventory, form.transactionDate ,invoice.inventoryAmount from invoice join inventory on inventory.inventoryNumber = invoice.inventoryNumber join form on form.formNumber = invoice.formNumber where inventory.nameInventory  = ?");
            findingStatement.setString(1, nameInventory);
            ResultSet resultSet = findingStatement.executeQuery();

            while (resultSet.next()) {
                search.add(new Search(resultSet.getString(1),resultSet.getDate(2),resultSet.getInt(3)));

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return search;
    }

    public ObservableList<Storage> searchStorage() {
        ObservableList<Storage> storage = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = stmt.executeQuery("Select *  From storage order by storage.storageName ASC;");
            while (resultSet.next()) {
                storage.add(new Storage(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return storage;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public List<Storage> getStorageList() {
        return storageList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

  public List<Form> getFormList() { return formList;}

}
