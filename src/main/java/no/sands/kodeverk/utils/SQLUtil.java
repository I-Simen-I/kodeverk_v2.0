package no.sands.kodeverk.utils;

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
     * Returns the SQL timestamp format TIMESTAMP('1900-01-01 10:00')
     *
     * @param date the date to use in the SQL timestamp format
     * @return SQL timestamp format
     */
    public static String getTimestampFormat(String date) {
        return TIMESTAMP.concat(date).concat("')");
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
                .concat("(").concat(insertColumns).concat(") ")
                .concat(VALUES).concat("(").concat(values).concat(");\n");
    }
}
