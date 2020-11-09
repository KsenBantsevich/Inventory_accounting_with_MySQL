package sample.database;

import java.sql.Date;

public class Form {
    private Integer formNumber;
    private Integer storageNumber;
    private Date transactionDate;
    private String employeeFio;
    private String employeePost;

    public Form(Integer formNumber, Integer storageNumber, Date transactionDate, String employeeFio, String employeePost){
        this.formNumber = formNumber;
        this.storageNumber = storageNumber;
        this.transactionDate = transactionDate;
        this.employeeFio = employeeFio;
        this.employeePost = employeePost;
    }

    public Integer getFormNumber() {
        return this.formNumber;
    }

    public Integer  getStorageNumber() {
        return this.storageNumber;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public String getEmployeeFio() {
        return this.employeeFio;
    }

    public String getEmployeePost() {
        return this.employeePost;
    }


}
