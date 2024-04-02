package com.Tests;

import com.BankingApp.BankAccount;
import com.BankingApp.CsvWriter;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;

public class CsvWriterTest {

    @Test
    @DisplayName("Test csv saving")
    public void testWriteAccountsToCsv() throws IOException {

        Path tempFile = Files.createTempFile("test", ".csv");


        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount(1000, "ID001"));
        accounts.add(new BankAccount(2000, "ID002"));

        CsvWriter.writeAccountsToCsv(accounts, tempFile.toString());


        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile.toString()))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }


            assertEquals(accounts.size(), lines.size());


            assertTrue(lines.contains("ID001,1000.0"));
            assertTrue(lines.contains("ID002,2000.0"));
        }

        Files.deleteIfExists(tempFile);
    }


}
