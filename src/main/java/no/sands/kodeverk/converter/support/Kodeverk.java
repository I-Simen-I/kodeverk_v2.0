package no.sands.kodeverk.converter.support;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author Øyvind Strømmen
 */
public class Kodeverk {

    public static final Map<RequiredHeaderValue, DataType> headerDataTypeMap = ImmutableMap.<RequiredHeaderValue, DataType>builder()
            .put(RequiredHeaderValue.DATO_FOM, DataType.DATE)
            .put(RequiredHeaderValue.DATO_TOM, DataType.DATE)
            .put(RequiredHeaderValue.ER_GYLDIG, DataType.CHARACTERS)
            .put(RequiredHeaderValue.DATO_OPPRETTET, DataType.TIMESTAMP)
            .put(RequiredHeaderValue.OPPRETTET_AV, DataType.CHARACTERS)
            .put(RequiredHeaderValue.DATO_ENDRET, DataType.TIMESTAMP)
            .put(RequiredHeaderValue.ENDRET_AV, DataType.CHARACTERS)
            .build();

    private String name;

    private Header header;

    private DataTypes dataTypes;

    private List<Row> rows;

    public Kodeverk(List<String[]> rawKodeverk) {

        header = new Header().withRawValues(rawKodeverk.remove(0));
        dataTypes = new DataTypes(rawKodeverk.remove(0));

        for (String [] row : rawKodeverk) {
            addRow(new Row(row));
        }
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void addRow(Row row) {
        rows.add(row);
    }
}
