package sample.database;

import java.sql.Date;

public class Search{
    private String nameInventory;
    private Date transactionDate;
    private Integer inventoryAmount;

    public Search(String nameInventory, Date transactionDate, Integer inventoryAmount){
        this.nameInventory = nameInventory;
        this.transactionDate = transactionDate;
        this.inventoryAmount = inventoryAmount;

    }

    public String getNameInventory(){
        return this.nameInventory;
    }

    public Date getTransactionDate(){
        return this.transactionDate;
    }

    public Integer getInventoryAmount(){
        return this.inventoryAmount;
    }
}
