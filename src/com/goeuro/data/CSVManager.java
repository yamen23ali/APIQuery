/*
 * This project was made as test for GoEuro
 */
package com.goeuro.data;

import java.io.FileWriter;
import com.opencsv.CSVWriter;

/**
 *
 * @author Yamen Ali
 */
public class CSVManager {

    private CSVWriter writer;

    //============ Constructor ============//
    public CSVManager() {
        initializeOutputFile("default.csv");
    }

    public CSVManager(String fileName) {
        initializeOutputFile(fileName);
    }
    //============ Constructor ============//

    /**
     * This function will set and initialize output file
     *
     * @params fileName
     */
    public void initializeOutputFile(String fileName) {
        try {
            writer = new CSVWriter(new FileWriter(fileName));
        } catch (Exception e) {
            System.out.println("Error Creating File : " + e.getMessage());
        }
    }

    /**
     * This function will write row in the out put .csv file
     *
     * @param row an array of row data
     */
    public void writeRow(String[] row) {
        writer.writeNext(row);
    }

    /**
     * Close output file writer
     */
    public void close() {
        try {
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Closing the file : " + e.getMessage());
        }
    }

}
