package no.sands.kodeverk.converter.support;

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

    private String name;

    private Header header;

    private DataTypes dataTypes;

    private List<Row> rows;

    public Kodeverk withRawValues(List<String[]> rawKodeverk) {
        header = new Header().withKodeverk(this).withRawValues(rawKodeverk.remove(0));
        dataTypes = new DataTypes().withKodeverk(this).withRawValues(rawKodeverk.remove(0));

//        for (String [] row : rawKodeverk) {
//            addRow(new Row(row));
//        }
        return this;
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

    public void addRow(Row row) {
        rows.add(row);
    }
}
