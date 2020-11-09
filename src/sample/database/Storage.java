package sample.database;

public class Storage {
    private String storageNumber;
    private String storageName;
    private String storagePhone;

    public Storage(String storageNumber, String storageName, String storagePhone) {
        this.storageName = storageName;
        this.storageNumber = storageNumber;
        this.storagePhone = storagePhone;
    }

    public String getStorageName() {
        return this.storageName;
    }

    public String getStoragePhone() {  return this.storagePhone; }

    public String getStorageNumber() {return this.storageNumber; }
}
