package no.sands.kodeverk.enums;

/**
 * @author Simen Søhol
 */
public enum SQLEnum {
    INSERT_INTO("INSERT INTO "),
    TABLE_PREFIX("T_"),
    VALUES(") VALUES ("),
    TIMESTAMP("TIMESTAMP("),
    DATE("DATE("),
    COMMA(","),
    SQL_ENDING(");\n");

    private String sqlValue;

    private SQLEnum(String value) {
        setSqlValue(value);
    }

    private String getSqlValue() {
        return sqlValue;
    }

    private void setSqlValue(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    @Override
    public String toString() {
        return getSqlValue();
    }
}
