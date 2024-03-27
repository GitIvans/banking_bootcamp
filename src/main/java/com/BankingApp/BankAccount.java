package com.BankingApp;

class BankAccount {
    private double balance;

    public BankAccount() {

    }


    public BankAccount(double balance) {
        this.balance = balance;
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