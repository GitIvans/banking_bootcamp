package com.BankingApp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void writeAccountsToCsv(List<BankAccount> accounts, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {

//            writer.write("Account ID,Balance\n");


            for (BankAccount account : accounts) {
                writer.write(account.getUId() + "," + account.getBalance() + "\n");
            }

            System.out.println("Accounts data has been written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing accounts data to CSV file: " + e.getMessage());
        }
    }
}
