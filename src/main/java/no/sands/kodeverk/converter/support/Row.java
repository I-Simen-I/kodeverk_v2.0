package no.sands.kodeverk.converter.support;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Øyvind Strømmen
 */
public class Row {

    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public Row(String[] rawRow) {
        for (String rawContent : rawRow) {
            addColumn(new Column(DataType.TIMESTAMP, rawContent));
        }
    }

    public void addColumn(Column column) {
        columns.add(column);

        //IF row is first row
        //  only alow meta info.
        //  validate presence of obligatory columns.

        //IF row is second row
        //  only allow data types.

        //IF row is any other row
        //
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object == this) {
            return true;
        }

        if (object.getClass() != getClass()) {
            return false;
        }

        Row row = (Row) object;
        return new EqualsBuilder().append(columns, row.getColumns()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(43, 45).append(columns).toHashCode();
    }
}
