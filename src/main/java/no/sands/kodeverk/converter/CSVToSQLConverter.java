package no.sands.kodeverk.converter;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import static no.sands.kodeverk.common.CommonVariables.ENCODING_WINDOWS_1252;
import static no.sands.kodeverk.common.CommonVariables.KODEVERK_FILE_PATH;
import static no.sands.kodeverk.common.CommonVariables.SQL_EMPTY_VALUE;
import static no.sands.kodeverk.common.CommonVariables.SQL_FILE_PATH;
import static no.sands.kodeverk.utils.FileUtil.getFileName;
import static no.sands.kodeverk.utils.FileUtil.getFilesInFolder;
import static no.sands.kodeverk.utils.FileUtil.readCSVFile;
import static no.sands.kodeverk.utils.SQLUtil.convertCSVValuesToSQlValues;
import static no.sands.kodeverk.utils.SQLUtil.createInsertStatement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import no.sands.kodeverk.domain.Kodeverk;
import no.sands.kodeverk.utils.FileUtil;

/**
 * @author Simen Søhol
 */
public class CSVToSQLConverter {
    public Map<String, Integer> generateSQL() throws Exception {
        FileUtil.createDirectory(SQL_FILE_PATH);

        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(SQL_FILE_PATH + "inserts.sql"), ENCODING_WINDOWS_1252);
        Map<String, Integer> insertStats = new HashMap<>();

        int insertCounter;

        for (File file : getFilesInFolder(KODEVERK_FILE_PATH)) {
            Kodeverk kodeverk = new Kodeverk(getFileName(file), readCSVFile(file));

            insertCounter = 0;

            for (int i = 0; i < kodeverk.getValues().size(); i++) {
                fileWriter.append(
                        createInsertStatement(kodeverk.getName(),
                                getHeaderAsString(kodeverk),
                                getValuesToInsert(kodeverk, i)));

                insertCounter++;
            }
            insertStats.put(kodeverk.getName(), insertCounter);
        }

        fileWriter.close();

        return insertStats;
    }

    /**
     * Returns the header in the kodeverk as a comma separated string
     *
     * @param kodeverk the to use
     * @return the header
     */
    private String getHeaderAsString(Kodeverk kodeverk) {
        StringBuilder rowBuilder = new StringBuilder();

        for (int i = 0; i < kodeverk.getNumberOfValidInsertValues(); i++) {
            rowBuilder.append(kodeverk.getHeader()[i]);
            rowBuilder.append(addCommmaSeparator(i, kodeverk.getNumberOfValidInsertValues()));
        }
        return rowBuilder.toString();
    }

    public String getValuesToInsert(Kodeverk kodeverk, int index) throws Exception {
        StringBuilder valueBuilder = new StringBuilder();

        for (int column = 0; column < kodeverk.getNumberOfValidInsertValues(); column++) {
            if (isNotEmpty(kodeverk.getColumnTypes()[column])) {
                valueBuilder.append(
                        convertCSVValuesToSQlValues(kodeverk.getColumnTypes()[column],
                                kodeverk.getValues().get(index)[column]));

                valueBuilder.append(addCommmaSeparator(column, kodeverk.getNumberOfValidInsertValues()));
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
