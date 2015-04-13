package no.sands.kodeverk.converter;

import no.sands.kodeverk.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static no.sands.kodeverk.common.CommonVariables.KODEVERK_FILE_PATH;
import static no.sands.kodeverk.common.CommonVariables.SQL_FILE_PATH;
import static no.sands.kodeverk.enums.ColumnType.*;
import static no.sands.kodeverk.enums.SQLEnum.*;
import static no.sands.kodeverk.utils.FileUtil.*;

/**
 * @author Simen S�hol
 */
public class CSVToSQLConverter {

    public Map<String, Integer> generateSQL() throws Exception {
        FileWriter fileWriter = new FileWriter(SQL_FILE_PATH + "inserts.sql");
        Map<String, Integer> insertStats = new HashMap<String, Integer>();

        int insertCounter;

        for (File file : getFilesInFolder(KODEVERK_FILE_PATH)) {
            List<String[]> csvLines = readCSVFile(file);
            insertCounter = 0;

            for (int i = 2; i < csvLines.size(); i++) {
                StringBuilder insertstatement = new StringBuilder(INSERT_INTO.toString());
                insertstatement.append(TABLE_PREFIX).append(getFileName(file)).append("(");
                insertstatement.append(getHeader(csvLines));
                insertstatement.append(VALUES);
                insertstatement.append(getValuesToInsert(csvLines, i));
                insertstatement.append(SQL_ENDING);

                fileWriter.append(insertstatement);
                insertCounter++;
            }
            insertStats.put(getFileName(file), insertCounter);
        }
        fileWriter.close();

        return insertStats;
    }

    /**
     * Returns the header in the csv file
     *
     * @param csvList the list go get the header from
     * @return the header
     */
    private String getHeader(List<String[]> csvList) {
        String[] header = csvList.get(0);
        StringBuilder rowBuilder = new StringBuilder();

        for (int i = 0; i < getNumberOfValidInsertValues(csvList); i++) {
            rowBuilder.append(header[i]);
            rowBuilder.append(addCommmaSeparator(i, getNumberOfValidInsertValues(csvList)));
        }
        return rowBuilder.toString();
    }

    private String getValuesToInsert(List<String[]> csvList, int index) throws Exception {
        StringBuilder valueBuilder = new StringBuilder();
        String[] columnType = csvList.get(1);

        for (int column = 0; column < getNumberOfValidInsertValues(csvList); column++) {
            if (StringUtils.isNotEmpty(columnType[column])) {
                validateColumnTypeForInsert(columnType[column], column, csvList.get(index), valueBuilder);
                valueBuilder.append(addCommmaSeparator(column, getNumberOfValidInsertValues(csvList)));
            }
        }

        return valueBuilder.toString();
    }

    /**
     * Validates the column values
     *
     * @param column       the columnType to validate
     * @param columnIndex  the index of the column
     * @param values       the row to insert
     * @param valueBuilder the StringBuilder
     * @throws Exception
     */
    private void validateColumnTypeForInsert(String column, int columnIndex, String[] values, StringBuilder valueBuilder) throws Exception {
        if (column.charAt(0) == TEXT_COLUMN.getPrefix()) {
            valueBuilder.append("'").append(values[columnIndex]).append("'");
        } else if (column.charAt(0) == DATE_COLUMN.getPrefix()) {
            if (DateUtil.isDateValid(values[columnIndex])) {
                valueBuilder.append(DATE).append(values[columnIndex]).append(")");
            } else {
                //TODO Legg til skikkelig exception her
                throw new Exception();
            }
        } else if (column.charAt(0) == TIMESTAMP_COLUMN.getPrefix()) {
            if (DateUtil.isTimestampValid(values[columnIndex])) {
                valueBuilder.append(TIMESTAMP).append(values[columnIndex]).append(")");
            } else {
                //TODO Legg til skikkelig exception her
                throw new Exception();
            }
        } else {
            valueBuilder.append(values[columnIndex]);
        }
    }

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return COMMA.toString();
        }
        return "";
    }
}
