package no.sands.kodeverk.generator;

import no.sands.kodeverk.helper.ExcelConverterHelper;

import java.io.FileWriter;
import java.io.IOException;

import static no.sands.kodeverk.common.CommonVariables.*;

/**
 * This class is used for creating csv files.
 *
 * @author Simen Søhol
 */
public class CSVGenerator {
    private ExcelConverterHelper excelConverterHelper = new ExcelConverterHelper();

    /**
     * Creates a csv file for each kodeverk
     *
     * @param kodeverkName the kodeverk to create
     * @param kodeverkList the kodeverkcodes to insert in the csv file
     */
    public void generateCSVFiles(final String kodeverkName, String[][] kodeverkList) throws IOException {
        String columnHeaderRow = excelConverterHelper.getHeaderRow(kodeverkList);
        String columnTypeRow = excelConverterHelper.getColumnTypeRow(kodeverkList);

        FileWriter fileWriter = new FileWriter(KODEVERK_FILE_PATH + kodeverkName + CSV_FILE);
        fileWriter.append(columnHeaderRow).append("\n");
        fileWriter.append(columnTypeRow).append("\n");

        for (int i = FIRS_KODEVERK_ROW_WITH_VALUES; i < kodeverkList.length; i++) {
            fileWriter.append(excelConverterHelper.getValuesAtRow(
                            kodeverkList[EXCEL_HEADER_ROW],
                            kodeverkList[EXCEL_COLUMN_TYPE_ROW],
                            kodeverkList[i], i)
            ).append("\n");
        }

        fileWriter.close();
    }
}
