package no.sands.kodeverk.generator;

import java.io.FileWriter;
import java.io.IOException;

import static no.sands.kodeverk.common.CommonVariables.*;

/**
 * This class is used for creating csv files.
 *
 * @author Simen Søhol
 */
public class CSVGenerator {

    /**
     * Creates a csv file for each kodeverk
     *
     * @param kodeverkName the kodeverk to create
     * @param kodeverkList the kodeverkcodes to insert in the csv file
     * @param columns      the number of columns in the codeverk
     */
    public void generateCSVFiles(final String kodeverkName, String[][] kodeverkList, int columns) throws IOException {
        String columnHeader = getColumnsAtRow(1, kodeverkList, columns);
        String columnStyle = getColumnsAtRow(0, kodeverkList, columns);

        FileWriter fileWriter = new FileWriter(KODEVERK_FILE_PATH + kodeverkName + CSV_FILE);
        fileWriter.append(columnHeader).append("\n");
        fileWriter.append(columnStyle).append("\n");

        for (int i = 2; i < kodeverkList.length; i++) {
            fileWriter.append(getColumnsAtRow(i, kodeverkList, columns)).append("\n");
        }

        fileWriter.close();
    }


    private String getColumnsAtRow(int row, String[][] kodeverkList, int columns) {
        StringBuilder columnBuilder = new StringBuilder();

        for (int column = 0; column < columns; column++) {
            if (kodeverkList[1][column].equals(COLUMN_DECODE) && row != 0 && row != 1) {
                columnBuilder.append("\"").append(kodeverkList[row][column]).append("\"");
            } else {
                columnBuilder.append(kodeverkList[row][column]);
            }
            columnBuilder.append(addCommmaSeparator(column, columns));
        }
        return columnBuilder.toString();
    }

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return ",";
        }
        return "";
    }
}
