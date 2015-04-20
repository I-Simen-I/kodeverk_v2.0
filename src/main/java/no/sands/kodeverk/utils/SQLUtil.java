package no.sands.kodeverk.utils;

import static no.sands.kodeverk.common.CommonVariables.DATE_COLUMN;
import static no.sands.kodeverk.common.CommonVariables.SQL_EMPTY_VALUE;
import static no.sands.kodeverk.common.CommonVariables.SQL_NULL_VALUE;
import static no.sands.kodeverk.common.CommonVariables.TEXT_COLUMN;
import static no.sands.kodeverk.common.CommonVariables.TIMESTAMP_COLUMN;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Simen Søhol
 */
public class SQLUtil {
    private static final String INSERT_INTO = "INSERT INTO";
    private static final String TABLE_PREFIX = "T_";
    private static final String VALUES = "VALUES";
    private static final String TIMESTAMP = "TIMESTAMP('";
    private static final String DATE = "DATE('";

    /**
     * Returns the SQL timestamp format TIMESTAMP('1900-01-01', '10:00')
     *
     * @param date the date to use in the SQL timestamp format
     * @return SQL timestamp format
     */
    public static String getTimestampFormat(String date) {
        String separatedDateTime = StringUtils.replace(date, " ", "','");
        return TIMESTAMP.concat(separatedDateTime).concat("')");
    }

    /**
     * Returns the SQL date format DATE('1900-01-01')
     *
     * @param date the date to use in SQL the date format
     * @return SQL date format
     */
    public static String getDateFormat(String date) {
        return DATE.concat(date).concat("')");
    }

    /**
     * Returns a SQL insert statement
     *
     * @param tableName     the name of the SQL table
     * @param insertColumns the columns to insert into
     * @param values        the values to insert
     * @return a insert statement
     */
    public static String createInsertStatement(String tableName, String insertColumns, String values) {
        return INSERT_INTO.concat(" ").concat(TABLE_PREFIX).concat(tableName)
                .concat(" (").concat(insertColumns).concat(") ")
                .concat(VALUES).concat(" (").concat(values).concat(");\n");
    }

    /**
     * Converts the kodeverk values to SQL values
     *
     * @param columnType  the columnType to validate
     * @param columnValue the column to convert
     */
    public static String convertCSVValuesToSQlValues(String columnType, String columnValue) {
        if (!columnValue.equals(SQL_EMPTY_VALUE)) {
            if (columnType.charAt(0) == TEXT_COLUMN) {
                return "'".concat(columnValue.concat("'"));
            } else if (columnType.charAt(0) == DATE_COLUMN) {
                return getDateFormat(columnValue);
            } else if (columnType.charAt(0) == TIMESTAMP_COLUMN) {
                return getTimestampFormat(columnValue);
            } else {
                return columnValue;
            }
        } else {
            return SQL_NULL_VALUE;
        }
    }
}
