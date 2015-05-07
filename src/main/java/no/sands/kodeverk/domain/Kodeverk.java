package no.sands.kodeverk.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A Kodeverk contains a {@link Header}, a {@link DataTypes} and one or more {@link Row}'s
 *
 * @author Øyvind Strømmen
 */
public class Kodeverk {

    private final String name;
    private final Header header;
    private final DataTypes dataTypes;
    private final List<Row> rows;

    private Kodeverk(KodeverkBuilder builder) {
        this.name = builder.name;
        this.header = builder.header;
        this.dataTypes = builder.dataTypes;
        this.rows = builder.rows;
    }

    /**
     * Get the name of this Kodeverk
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Header contained within this Kodeverk
     *
     * @return the Header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Get the DataTypes contained within this Kodeverk
     *
     * @return the Kodeverk
     */
    public DataTypes getDataTypes() {
        return dataTypes;
    }

    /**
     * Get the Row's contained within this Kodeverk
     *
     * @return a List of Row's
     */
    public List<Row> getRows() {
        return rows;
    }

    public static class KodeverkBuilder {

        private final String name;
        private final String[] rawHeader;
        private final String[] rawDataTypes;
        private final List<String[]> rawKodeverk;

        private Header header;
        private DataTypes dataTypes;
        private List<Row> rows = new ArrayList<>();

        public KodeverkBuilder(String name, String[] rawHeader, String[] rawDataTypes, List<String[]> rawKodeverk) {
            this.name = name;
            this.rawHeader = rawHeader;
            this.rawDataTypes = rawDataTypes;
            this.rawKodeverk = rawKodeverk;
        }

        /**
         * Validate the state of the builder and build a {@link Kodeverk} if valid.
         *
         * @return a complete Kodeverk if validation was successful
         */
        public Kodeverk build() {
            this.header = new Header.HeaderBuilder(this.rawHeader).build();
            this.dataTypes = new DataTypes.DataTypesBuilder(this.rawDataTypes, this.header).build();

            for (int rowNumber = 0; rowNumber < this.rawKodeverk.size(); rowNumber++) {
                this.rows.add(new Row.RowBuilder(this.rawKodeverk.get(rowNumber), rowNumber, this.header, this.dataTypes).build());
            }
            return new Kodeverk(this);
        }
    }
}
