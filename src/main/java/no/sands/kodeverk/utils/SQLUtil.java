package no.sands.kodeverk.utils;

import static no.sands.kodeverk.common.CommonVariables.SQL_NULL_VALUE;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.domain.Column;
import no.sands.kodeverk.domain.content.Characters;
import no.sands.kodeverk.domain.content.Date;
import no.sands.kodeverk.domain.content.Index;
import no.sands.kodeverk.domain.content.Timestamp;

/**
 * @author Simen Søhol
 */
public class SQLUtil {
    private static final String INSERT_INTO = "insert into";
    private static final String TABLE_PREFIX = "T_";
    private static final String VALUES = "VALUES";
    private static final String TIMESTAMP = "timestamp('";
    private static final String DATE = "date('";

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
     * @param column  the column to convert
     */
    public static String convertCSVValuesToSQlValues(Column column) {
        if (column.getContent() != null && StringUtils.isNotBlank(column.getContent().getContentAsString())) {
            if (column.getContent() instanceof Characters) {
                return makeValidSQLText(column.getContent().getContentAsString());
            } else if (column.getContent() instanceof Date) {
                return getDateFormat(column.getContent().getContentAsString());
            } else if (column.getContent() instanceof Timestamp) {
                return getTimestampFormat(column.getContent().getContentAsString());
            } else if (column.getContent() instanceof Index) {
                return column.getContent().getContentAsString();
            } else {
                return column.getContent().getContentAsString();
            }
        } else {
            return SQL_NULL_VALUE;
        }
    }

    /**
     * Converts the columnvalue to a valid SQL text
     *
     * @param columnText the text to edit
     * @return the valid SQL text
     */
    private static String makeValidSQLText(String columnText) {
        String validSQLText = columnText;

        if (columnText.contains("'")) {
            validSQLText = StringUtils.replace(columnText, "'", "''");
        }

        return "'".concat(validSQLText.concat("'"));
    }
}
