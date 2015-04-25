package no.sands.kodeverk.converter.support;

/**
 * @author �yvind Str�mmen
 */
public enum DataType {

    CHARACTERS("c"),
    TIMESTAMP("t"),
    DATE("d"),
    INDEX("i");

    private String value;

    private DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
