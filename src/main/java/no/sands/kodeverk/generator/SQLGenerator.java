package no.sands.kodeverk.generator;

import java.util.ArrayList;
import java.util.List;

import static no.sands.kodeverk.enums.ColumnType.*;
import static no.sands.kodeverk.enums.SQLEnum.*;

/**
 * @author Simen Søhol
 */
public class SQLGenerator {
    private List<String> sqlInsertList = new ArrayList<String>();

    public List<String> generateSQL(String kodeverkName, String[][] kodeverkList, int columns) {
        StringBuilder insertstatement;

        for (int i = 2; i < kodeverkList.length; i++) {
            insertstatement = new StringBuilder(INSERT_INTO.toString());
            insertstatement.append(TABLE_PREFIX).append(kodeverkName).append("(");
            insertstatement.append(generateColumn(kodeverkList, columns));
            insertstatement.append(VALUES);
            insertstatement.append(generageValues(i, kodeverkList, columns));
            insertstatement.append(SQL_ENDING);

            sqlInsertList.add(insertstatement.toString());
        }
        return sqlInsertList;
    }

    private String generateColumn(String[][] kodeverkConfigList, int columns) {
        final int columnRow = 1;
        StringBuilder columnbilder = new StringBuilder();

        for (int column = 0; column < columns; column++) {
            columnbilder.append(kodeverkConfigList[columnRow][column]);
            columnbilder.append(addCommmaSeparator(column, columns));
        }
        return columnbilder.toString();
    }

    private String generageValues(int i, String[][] kodeverkConfigList, int columns) {
        StringBuilder valuebilder = new StringBuilder();
        for (int column = 0; column < columns; column++) {
            if (kodeverkConfigList[0][column].charAt(0) == TEXT_COLUMN.getPrefix()) {
                valuebilder.append("'").append(kodeverkConfigList[i][column]).append("'");
            } else if (kodeverkConfigList[0][column].charAt(0) == DATE_COLUMN.getPrefix()) {
                valuebilder.append(DATE).append(kodeverkConfigList[i][column]).append(")");
            } else {
                if (kodeverkConfigList[0][column].charAt(0) == TIMESTAMP_COLUMN.getPrefix()) {
                    valuebilder.append(TIMESTAMP).append(kodeverkConfigList[i][column]).append(")");
                }
            }
            valuebilder.append(addCommmaSeparator(column, columns));
        }
        return valuebilder.toString();
    }

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return COMMA.toString();
        }
        return "";
    }
}
