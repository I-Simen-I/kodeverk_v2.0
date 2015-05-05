package no.sands.kodeverk.converter.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author Øyvind Strømmen
 */
public class Kodeverk {

    public static final Map<HeaderType, DataType> headerDataTypeMap = ImmutableMap.<HeaderType, DataType>builder()
            .put(HeaderType.DATO_FOM, DataType.DATE)
            .put(HeaderType.DATO_TOM, DataType.DATE)
            .put(HeaderType.ER_GYLDIG, DataType.CHARACTERS)
            .put(HeaderType.DATO_OPPRETTET, DataType.TIMESTAMP)
            .put(HeaderType.OPPRETTET_AV, DataType.CHARACTERS)
            .put(HeaderType.DATO_ENDRET, DataType.TIMESTAMP)
            .put(HeaderType.ENDRET_AV, DataType.CHARACTERS)
            .build();

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

    public String getName() {
        return name;
    }

    public Header getHeader() {
        return header;
    }

    public DataTypes getDataTypes() {
        return dataTypes;
    }

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

        public Kodeverk build() {
            Kodeverk kodeverk = new Kodeverk(this);

            this.header = new Header.HeaderBuilder(this.rawHeader, kodeverk).build();
            this.dataTypes = new DataTypes.DataTypesBuilder(this.rawDataTypes, kodeverk).build();

            for (int rowNumber = 0; rowNumber < this.rawKodeverk.size(); rowNumber++) {
                this.rows.add(new Row.RowBuilder(this.rawKodeverk.get(rowNumber), rowNumber).build());
            }
            return kodeverk;
        }
    }
}
