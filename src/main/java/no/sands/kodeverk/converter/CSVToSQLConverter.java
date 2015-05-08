package no.sands.kodeverk.converter;

import static no.sands.kodeverk.common.CommonVariables.ENCODING_WINDOWS_1252;
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
import java.util.List;
import java.util.Map;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.domain.Column;
import no.sands.kodeverk.domain.DataType;
import no.sands.kodeverk.domain.Kodeverk;
import no.sands.kodeverk.domain.Row;
import no.sands.kodeverk.domain.content.Characters.CharactersBuilder;
import no.sands.kodeverk.domain.content.ContentFactory;
import no.sands.kodeverk.domain.content.Date.DateBuilder;
import no.sands.kodeverk.domain.content.Index.IndexBuilder;
import no.sands.kodeverk.domain.content.Timestamp.TimeStampBuilder;
import no.sands.kodeverk.utils.FileUtil;

/**
 * @author Simen Søhol
 */
public class CSVToSQLConverter {

    static {
        ContentFactory.registerContent(DataType.CHARACTERS, new CharactersBuilder());
        ContentFactory.registerContent(DataType.DATE, new DateBuilder());
        ContentFactory.registerContent(DataType.TIMESTAMP, new TimeStampBuilder());
        ContentFactory.registerContent(DataType.INDEX, new IndexBuilder());
    }

    public Map<String, Integer> generateSQL() throws Exception {
        FileUtil.createDirectory(SQL_FILE_PATH);

        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(SQL_FILE_PATH + "inserts.sql"), ENCODING_WINDOWS_1252);
        Map<String, Integer> insertStats = new HashMap<>();

        for (File file : getFilesInFolder(CommonVariables.KODEVERK_FILE_PATH)) {
            List<String[]> csvLines = readCSVFile(file);

            System.out.println(getFileName(file));
            Kodeverk kodeverk = new Kodeverk.KodeverkBuilder(getFileName(file), csvLines.remove(0), csvLines.remove(0), csvLines).build();

            for (Row row : kodeverk.getRows()) {
                fileWriter.append(createInsertStatement(kodeverk.getName(), getHeaderAsString(kodeverk), getValuesToInsert(row)));
            }
            insertStats.put(kodeverk.getName(), kodeverk.getRows().size());
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

        for (int i = 0; i < kodeverk.getDataTypes().getValues().size(); i++) {
            rowBuilder.append(kodeverk.getHeader().getValues().get(i));
            rowBuilder.append(addCommmaSeparator(i, kodeverk.getDataTypes().getValues().size()));
        }
        return rowBuilder.toString();
    }

    public String getValuesToInsert(Row row) throws Exception {
        StringBuilder valueBuilder = new StringBuilder();

        for (Column column : row.getColumns()) {
            valueBuilder.append(convertCSVValuesToSQlValues(column));

            valueBuilder.append(addCommmaSeparator(column.getColummNumber(), row.getColumns().size()));
        }
        return valueBuilder.toString();
    }

    private String addCommmaSeparator(int columnIndex, int columnSize) {
        if (columnIndex != columnSize - 1) {
            return ",";
        }
        return SQL_EMPTY_VALUE;
    }
}
