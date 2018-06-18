package com.fuck.off.fuckoffasaservice.utilities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class ExcelToCsvBankTransactions {

    public void main() throws IOException {
        File transactionFileDirectory = new File("/home/laptop/Dropbox/personal/expenses/whetstone-consulting/2018");
        File[] files = FILE.findFiles(transactionFileDirectory, ".csv");

        int index = 1;
        File outputDirectory = new File("./transactions");
        if (!outputDirectory.mkdirs()) {
            System.out.println("Couldn't create output directory : " + outputDirectory.getAbsolutePath());
        }
        for (final File file : files) {
            // "BE31 0689 0683 6455 2018-06-02 9-13-07 1.csv"
            System.out.println(file.getAbsolutePath());
            Reader in = new FileReader(file.getAbsolutePath());

            CSVFormat csvFormat = CSVFormat.DEFAULT;
            csvFormat = csvFormat.withDelimiter(';');
            csvFormat = csvFormat.withRecordSeparator(';');
            csvFormat = csvFormat.withHeader();
            CSVParser records = csvFormat.parse(in);

            File outputFile = new File(outputDirectory, index + ".csv");
            if (!outputFile.createNewFile()) {
                System.out.println("Couldn't create new file : " + outputFile.getAbsolutePath());
            }
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write("Date,Description,Amount");

            for (final CSVRecord record : records) {
                String date = record.get("Boekingsdatum");
                String description = record.get("Mededelingen");
                String amount = record.get("Bedrag");
                amount = amount.replaceAll(",", ".");
                description = strip(description);

                // System.out.println(date + ", " + description + ", " + amount);
                fileWriter.write("\n" + date + "," + description + "," + amount);
            }
            fileWriter.close();
            index++;
        }
    }

    private static String strip(final String string) {
        return string.replaceAll("[^A-Za-z0-9]", " ");
    }

}