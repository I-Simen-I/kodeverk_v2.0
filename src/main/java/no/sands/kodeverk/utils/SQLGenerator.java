package no.sands.kodeverk.utils;

import no.sands.kodeverk.common.ColumType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Søhol
 */
public class SQLGenerator {
    private List<String> sqlInsertList = new ArrayList<>();

    public List<String> generateSQL(String kodeverkName, String[][] kodeverkList, int columns) {
        StringBuilder insertstatement;

        for (int i = 2; i < kodeverkList.length; i++) {
            insertstatement = new StringBuilder("INSERT INTO " + kodeverkName + "(");
            insertstatement.append(generateColumn(kodeverkList, columns));
            insertstatement.append(") VALUES(");
            insertstatement.append(generageValues(i, kodeverkList, columns));
            insertstatement.append(");");

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
            if (kodeverkConfigList[0][column].charAt(0) == ColumType.TEXT_COLUMN.getPrefix()) {
                valuebilder.append("'").append(kodeverkConfigList[i][column]).append("'");
            } else if (kodeverkConfigList[0][column].charAt(0) == ColumType.DATE_COLUMN.getPrefix()) {
                valuebilder.append("date(").append(kodeverkConfigList[i][column]).append(")");
            } else if (kodeverkConfigList[0][column].charAt(0) == ColumType.TIMESTAMP_COLUMN.getPrefix()) {
                valuebilder.append("timestamp(").append(kodeverkConfigList[i][column]).append(")");
            }
            valuebilder.append(addCommmaSeparator(column, columns));
        }
        return valuebilder.toString();
    }

    private String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return ", ";
        }
        return "";
    }
}
