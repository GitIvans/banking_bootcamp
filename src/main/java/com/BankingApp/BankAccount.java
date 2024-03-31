package com.BankingApp;

class BankAccount {

    private double balance;
    private String uId;



    public BankAccount() {

    }


    public BankAccount(double balance, String uId) {
        this.balance = balance;
        this.uId = uId;

    }


    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }


    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited -> " + amount);
    }


    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn -> " + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }


    public void printBalance() {
        System.out.println("Balance -> " + balance);
    }

    public void transfer(BankAccount receiver, double amount) {
        if (balance >= amount) {
            withdraw(amount);
            receiver.deposit(amount);
            System.out.println("Transferred " + amount + " to another account.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }


}