package com.hm.thebudgetapp;


public class Budget {

    private int id;
    private String name;
    private String category;
    private double balance;

    private Transacation[] transactions;

    public Budget(){}

    public Budget(String name, String category, double balance){
        this.name = name;
        this.category = category;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Transacation[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transacation[] transactions) {
        this.transactions = transactions;
    }
}
