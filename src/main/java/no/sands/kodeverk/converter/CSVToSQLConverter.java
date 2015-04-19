package no.sands.kodeverk.converter;

import no.sands.kodeverk.helper.CSVErrorHelper;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static no.sands.kodeverk.common.CommonVariables.*;
import static no.sands.kodeverk.utils.FileUtil.*;
import static no.sands.kodeverk.utils.SQLUtil.convertCSVValuesToSQlValues;
import static no.sands.kodeverk.utils.SQLUtil.createInsertStatement;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Simen Søhol
 */
public class CSVToSQLConverter {
    private CSVErrorHelper csvErrorHelper = new CSVErrorHelper();

    public Map<String, Integer> generateSQL() throws Exception {
        FileWriter fileWriter = new FileWriter(SQL_FILE_PATH + "inserts.sql");
        Map<String, Integer> insertStats = new HashMap<String, Integer>();
        List<String> errorList = new ArrayList<String>();

        int insertCounter;

        for (File file : getFilesInFolder(KODEVERK_FILE_PATH)) {
            List<String[]> csvLines = readCSVFile(file);
            insertCounter = 0;

            for (int i = FIRS_KODEVERK_ROW_WITH_VALUES; i < csvLines.size(); i++) {
                errorList.addAll(rowContainError(getFileName(file), csvLines, i));
            }

            if (errorList.isEmpty()) {
                for (int i = FIRS_KODEVERK_ROW_WITH_VALUES; i < csvLines.size(); i++) {
                    fileWriter.append(createInsertStatement(getFileName(file), getHeader(csvLines), getValuesToInsert(csvLines, i)));
                    insertCounter++;
                }
            }
            insertStats.put(getFileName(file), insertCounter);
        }
        for (String error : errorList) {
            System.out.println(error);
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
        String[] header = csvList.get(CSV_HEADER_ROW);
        StringBuilder rowBuilder = new StringBuilder();

        for (int i = 0; i < getNumberOfValidInsertValues(csvList); i++) {
            rowBuilder.append(header[i]);
            rowBuilder.append(addCommmaSeparator(i, getNumberOfValidInsertValues(csvList)));
        }
        return rowBuilder.toString();
    }

    private String getValuesToInsert(List<String[]> csvList, int index) throws Exception {
        StringBuilder valueBuilder = new StringBuilder();
        String[] columnType = csvList.get(CSV_COLUMN_TYPE_ROW);

        for (int column = 0; column < getNumberOfValidInsertValues(csvList); column++) {
            if (isNotEmpty(columnType[column])) {
                valueBuilder.append(convertCSVValuesToSQlValues(columnType[column], csvList.get(index)[column]));
                valueBuilder.append(addCommmaSeparator(column, getNumberOfValidInsertValues(csvList)));
            }
        }

        return valueBuilder.toString();
    }

    private List<String> rowContainError(String fileName, List<String[]> csvList, int row) {
        int numberOfValidColumns = getNumberOfValidInsertValues(csvList);

        return csvErrorHelper.rowContainError(fileName, csvList, numberOfValidColumns, row);
    }

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return ",";
        }
        return SQL_EMPTY_VALUE;
    }
}
