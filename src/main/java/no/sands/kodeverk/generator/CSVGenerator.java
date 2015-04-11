package no.sands.kodeverk.generator;

import no.sands.kodeverk.common.CommonVariables;

import java.io.FileWriter;
import java.io.IOException;

import static no.sands.kodeverk.enums.ColumnType.DECODE;
import static no.sands.kodeverk.enums.FileType.CSV;
import static no.sands.kodeverk.enums.SQLEnum.COMMA;

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

        FileWriter fileWriter = new FileWriter(CommonVariables.KODEVERK_FILE_PATH + kodeverkName + CSV.getFiletype());
        fileWriter.append(columnHeader).append("\n");
        fileWriter.append(columnStyle).append("\n");

        for (int i = 2; i < kodeverkList.length; i++) {
            fileWriter.append(getColumnsAtRow(i, kodeverkList, columns)).append("\n");
        }

        fileWriter.close();
    }


    private String getColumnsAtRow(int row, String[][] kodeverkList, int columns) {
        StringBuilder columnbilder = new StringBuilder();

        for (int column = 0; column < columns; column++) {
            if (kodeverkList[1][column].equals(DECODE.getColumnType())) {
                columnbilder.append("\"").append(kodeverkList[row][column]).append("\"");
            } else {
                columnbilder.append(kodeverkList[row][column]);
            }
            columnbilder.append(addCommmaSeparator(column, columns));
        }
        return columnbilder.toString();
    }

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return COMMA.toString();
        }
        return "";
    }
}
