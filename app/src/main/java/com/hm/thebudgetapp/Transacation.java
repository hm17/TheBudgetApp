package com.hm.thebudgetapp;

import java.util.Date;

public class Transacation {

    private int id;
    private String vendor;
    private String description;
    private String category;
    private double amount;
    private int budgetId;
    private Date date;

    public Transacation(String vendor, String description, double amount, int budgetId) {
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
        this.budgetId = budgetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }
}
