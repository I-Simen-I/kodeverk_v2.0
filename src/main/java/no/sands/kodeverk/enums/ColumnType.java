package no.sands.kodeverk.enums;

/**
 * @author Simen Søhol
 */
public enum ColumnType {
    TEXT_COLUMN('c'),
    TIMESTAMP_COLUMN('t'),
    DATE_COLUMN('d'),
    INDEX_COLUMN('i');

    private char prefix;

    private ColumnType(char prefix) {
        setPrefix(prefix);
    }


    public char getPrefix() {
        return prefix;
    }

    private void setPrefix(char prefix) {
        this.prefix = prefix;
    }
}
