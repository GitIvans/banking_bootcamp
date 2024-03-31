package com.BankingApp;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankingSystem {

    private List<BankAccount> accounts;

    public BankingSystem() {
        this.accounts = loadAccountsFromCsv("accounts.csv");
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Create new account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Print balance");
            System.out.println("6. Show all accounts");
            System.out.println("7. Save accounts");
            System.out.println("8. Exit");
            System.out.print("Enter your choice -> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    printBalance(scanner);
                    break;
                case 6:
                    showAllAccounts();
                    break;
                case 7:
                    saveAccountsToCsv();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }

    private void createAccount(Scanner scanner) {
        System.out.print("Enter initial balance for the new account: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Enter UID for the new account: ");
        String initialUId = scanner.next();
        BankAccount account = new BankAccount(initialBalance, initialUId);
        accounts.add(account);
        saveAccountsToCsv();
        System.out.println("New account created successfully.");
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter UID of the account to deposit into: ");
        String uid = scanner.next();
        BankAccount account = findAccountByUid(uid);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            saveAccountsToCsv();
        } else {
            System.out.println("Account not found.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter UID of the account to withdraw from: ");
        String uid = scanner.next();
        BankAccount account = findAccountByUid(uid);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            saveAccountsToCsv();
        } else {
            System.out.println("Account not found.");
        }
    }

    private void transfer(Scanner scanner) {
        System.out.print("Enter UID of the account to transfer from: ");
        String uidFrom = scanner.next();
        BankAccount accountFrom = findAccountByUid(uidFrom);
        if (accountFrom != null) {
            System.out.print("Enter UID of the account to transfer to: ");
            String uidTo = scanner.next();
            BankAccount accountTo = findAccountByUid(uidTo);
            if (accountTo != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                accountFrom.transfer(accountTo, amount);
                saveAccountsToCsv();
            } else {
                System.out.println("Destination account not found.");
            }
        } else {
            System.out.println("Source account not found.");
        }
    }

    private void printBalance(Scanner scanner) {
        System.out.print("Enter UID of the account to print balance: ");
        String uid = scanner.next();
        BankAccount account = findAccountByUid(uid);
        if (account != null) {
            account.printBalance();
        } else {
            System.out.println("Account not found.");
        }
    }

    private BankAccount findAccountByUid(String uid) {
        for (BankAccount account : accounts) {
            if (account.getUId().equals(uid)) {
                return account;
            }
        }
        return null;
    }

    private void showAllAccounts() {
        System.out.println("All Accounts:");
        for (BankAccount account : accounts) {
            System.out.println("Account ID: " + account.getUId() + ", Balance: " + account.getBalance());
        }
    }

    private void saveAccountsToCsv() {
        CsvWriter.writeAccountsToCsv(accounts, "accounts.csv");
    }

    private List<BankAccount> loadAccountsFromCsv(String filename) {
        List<BankAccount> loadedAccounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String accountId = parts[0];
                double balance = Double.parseDouble(parts[1]);
                loadedAccounts.add(new BankAccount(balance, accountId));
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts data from CSV file: " + e.getMessage());
        }
        return loadedAccounts;
    }
}

