package no.sands.kodeverk.generator;

import static org.apache.commons.lang3.StringUtils.trim;

import static no.sands.kodeverk.common.CommonVariables.CSV_FILE;
import static no.sands.kodeverk.common.CommonVariables.EXCEL_COLUMN_TYPE_ROW;
import static no.sands.kodeverk.common.CommonVariables.EXCEL_HEADER_ROW;
import static no.sands.kodeverk.common.CommonVariables.FIRST_KODEVERK_ROW_WITH_VALUES;
import static no.sands.kodeverk.common.CommonVariables.KODEVERK_FILE_PATH;
import static no.sands.kodeverk.utils.DateUtil.convertDateString;
import static no.sands.kodeverk.utils.DateUtil.convertTimestampString;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

import no.sands.kodeverk.helper.ExcelConverterHelper;

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
    public void generateCSVFiles(final String kodeverkName, List<String[]> kodeverkList) throws IOException {
        String[] columnHeaderRow = kodeverkList.get(EXCEL_HEADER_ROW);
        String[] columnTypeRow = kodeverkList.get(EXCEL_COLUMN_TYPE_ROW);

        CSVWriter fileWriter = new CSVWriter(new FileWriter(KODEVERK_FILE_PATH + kodeverkName + CSV_FILE));
        fileWriter.writeNext(columnHeaderRow);
        fileWriter.writeNext(columnTypeRow);

        for (int row = FIRST_KODEVERK_ROW_WITH_VALUES; row < kodeverkList.size(); row++) {
            for (int column = 0; column < kodeverkList.get(row).length; column++) {
                if (excelConverterHelper.isColumnADate(columnTypeRow, column)) {
                    kodeverkList.get(row)[column] = convertDateString(trim(kodeverkList.get(row)[column]));
                } else if (excelConverterHelper.isColumnATimestamp(columnTypeRow, column)) {
                    kodeverkList.get(row)[column] = convertTimestampString(trim(kodeverkList.get(row)[column]));
                }
            }
            fileWriter.writeNext(kodeverkList.get(row));
        }

        fileWriter.close();
    }
}