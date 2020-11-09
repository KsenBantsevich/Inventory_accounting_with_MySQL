package sample.database;

public class Invoice {
    private Integer invoiceNumber;
    private Integer formNumber;
    private Integer inventoryNumber;
    private Integer inventoryAmount;

    public Invoice(Integer invoiceNumber, Integer formNumber, Integer inventoryNumber, Integer inventoryAmount){
        this.invoiceNumber = invoiceNumber;
        this.formNumber = formNumber;
        this.inventoryNumber = inventoryNumber;
        this.inventoryAmount = inventoryAmount;
    }

    public Integer getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public Integer getFormNumber() {
        return this.formNumber;
    }

    public Integer getInventoryNumber() {
        return this.inventoryNumber;
    }

    public Integer getInventoryAmount() {
        return this.inventoryAmount;
    }

}
