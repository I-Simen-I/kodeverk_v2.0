package no.sands.kodeverk.converter.support;

/**
 * @author �yvind Str�mmen
 */
public enum DataType {

    CHARACTERS("c"),
    TIMESTAMP("t"),
    DATE("d"),
    INDEX("i");

    private String name;

    private DataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
