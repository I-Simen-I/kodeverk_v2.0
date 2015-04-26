package no.sands.kodeverk.converter;

import no.sands.kodeverk.domain.Kodeverk;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
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
    public Map<String, Integer> generateSQL() throws Exception {
        FileWriter fileWriter = new FileWriter(SQL_FILE_PATH + "inserts.sql");
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
