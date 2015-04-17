package no.sands.kodeverk.generator;

import com.opencsv.CSVWriter;
import no.sands.kodeverk.helper.ExcelConverterHelper;
import no.sands.kodeverk.utils.DateUtil;

import java.io.FileWriter;
import java.io.IOException;

import static no.sands.kodeverk.common.CommonVariables.*;
import static no.sands.kodeverk.utils.DateUtil.convertDateString;
import static org.apache.commons.lang3.StringUtils.trim;

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
        String[] columnHeaderRow = kodeverkList[EXCEL_HEADER_ROW];
        String[] columnTypeRow = kodeverkList[EXCEL_COLUMN_TYPE_ROW];

        CSVWriter fileWriter = new CSVWriter(new FileWriter(KODEVERK_FILE_PATH + kodeverkName + CSV_FILE));
        fileWriter.writeNext(columnHeaderRow);
        fileWriter.writeNext(columnTypeRow);

        for (int row = FIRS_KODEVERK_ROW_WITH_VALUES; row < kodeverkList.length; row++) {

            for (int column = 0; column < kodeverkList[row].length; column++) {
                if (excelConverterHelper.isColumnADate(columnTypeRow, column)) {
                    kodeverkList[row][column] = convertDateString(trim(kodeverkList[row][column]));
                } else {
                    if (excelConverterHelper.isColumnATimestamp(columnTypeRow, column)) {
                        kodeverkList[row][column] = DateUtil.convertTimestampString(trim(kodeverkList[row][column]));
                    }
                }
            }
            fileWriter.writeNext(kodeverkList[row]);
        }

        fileWriter.close();
    }
}