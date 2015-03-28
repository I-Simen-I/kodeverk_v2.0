package no.sands.kodeverk.common;

/**
 * @author Simen Søhol
 */
public enum SQLEnum {
    INSERT_INTO("INSERT INTO "),
    TABLE_PREFIX("T_"),
    VALUES(") VALUES ("),
    TIMESTAMP("TIMESTAMP("),
    DATE("DATE("),
    COMMA(", "),
    SQL_ENDING(");");

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
