package sample.database;

public class Inventory {
    private Integer inventoryNumber;
    private String nameInventory;
    private String typeInventory;

    public Inventory(Integer inventoryNumber, String nameInventory, String typeInventory){
        this.inventoryNumber = inventoryNumber;
        this.nameInventory = nameInventory;
        this.typeInventory = typeInventory;
    }
    public Integer getInventoryNumber(){
        return this.inventoryNumber;
    }

    public String getNameInventory(){
        return this.nameInventory;
    }

    public String getTypeInventory(){
        return this.typeInventory;
    }


}
