package no.sands.kodeverk.common;

/**
 * @author Simen Søhol
 */
public enum ColumType {
    TEXT_COLUMN('c'),
    TIMESTAMP_COLUMN('t'),
    DATE_COLUMN('d');

    private char prefix;

    private ColumType(char prefix) {
        setPrefix(prefix);
    }

    public char getPrefix() {
        return prefix;
    }

    private void setPrefix(char prefix) {
        this.prefix = prefix;
    }
}
