package com.BankingApp;

import java.util.Scanner;

public class Banking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount(500);

        account1.deposit(100);
        account2.withdraw(50);


        account2.transfer(account1, 200);


        System.out.print("Account 1 Balance -> ");
        account1.printBalance();
        System.out.print("Account 2 Balance -> ");
        account2.printBalance();

        scanner.close();
    }
}