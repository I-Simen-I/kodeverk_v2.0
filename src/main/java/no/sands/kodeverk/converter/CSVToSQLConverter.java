package no.sands.kodeverk.converter;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import static no.sands.kodeverk.common.CommonVariables.CSV_COLUMN_TYPE_ROW;
import static no.sands.kodeverk.common.CommonVariables.CSV_HEADER_ROW;
import static no.sands.kodeverk.common.CommonVariables.FIRS_KODEVERK_ROW_WITH_VALUES;
import static no.sands.kodeverk.common.CommonVariables.KODEVERK_FILE_PATH;
import static no.sands.kodeverk.common.CommonVariables.SQL_EMPTY_VALUE;
import static no.sands.kodeverk.common.CommonVariables.SQL_FILE_PATH;
import static no.sands.kodeverk.utils.FileUtil.getFileName;
import static no.sands.kodeverk.utils.FileUtil.getFilesInFolder;
import static no.sands.kodeverk.utils.FileUtil.getNumberOfValidInsertValues;
import static no.sands.kodeverk.utils.FileUtil.readCSVFile;
import static no.sands.kodeverk.utils.SQLUtil.convertCSVValuesToSQlValues;
import static no.sands.kodeverk.utils.SQLUtil.createInsertStatement;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simen Søhol
 */
public class CSVToSQLConverter {
    public Map<String, Integer> generateSQL() throws Exception {
        FileWriter fileWriter = new FileWriter(SQL_FILE_PATH + "inserts.sql");
        Map<String, Integer> insertStats = new HashMap<>();

        int insertCounter;

        for (File file : getFilesInFolder(KODEVERK_FILE_PATH)) {
            List<String[]> csvLines = readCSVFile(file);
            insertCounter = 0;
            System.out.println(getFileName(file));

            for (int i = FIRS_KODEVERK_ROW_WITH_VALUES; i < csvLines.size(); i++) {
                fileWriter.append(createInsertStatement(getFileName(file), getHeader(csvLines), getValuesToInsert(csvLines, i)));
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

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return ",";
        }
        return SQL_EMPTY_VALUE;
    }
}
