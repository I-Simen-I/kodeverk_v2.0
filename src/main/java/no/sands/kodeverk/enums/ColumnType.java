package no.sands.kodeverk.enums;

/**
 * @author Simen Søhol
 */
public enum ColumnType {
    TEXT_COLUMN('c'),
    TIMESTAMP_COLUMN('t'),
    DATE_COLUMN('d'),
    DECODE("decode");

    private char prefix;
    private String columnType;

    private ColumnType(char prefix) {
        setPrefix(prefix);
    }

    private ColumnType(String columnType) {
        setColumnType(columnType);
    }

    public char getPrefix() {
        return prefix;
    }

    private void setPrefix(char prefix) {
        this.prefix = prefix;
    }

    public String getColumnType() {
        return columnType;
    }

    private void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
