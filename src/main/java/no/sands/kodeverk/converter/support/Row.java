package no.sands.kodeverk.converter.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Øyvind Strømmen
 */
public class Row {

    private final List<Column> columns;

    private final int rowNumber;

    private Row(RowBuilder builder) {
        this.columns = builder.columns;
        this.rowNumber = builder.rowNumber;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public static class RowBuilder {

        private final String [] rawColumns;

        private final int rowNumber;

        private final Header header;

        private final DataTypes dataTypes;

        private List<Column> columns = new ArrayList<>();

        public RowBuilder(String [] rawColumns, int rowNumber, Header header, DataTypes dataTypes) {
            this.rawColumns = rawColumns;
            this.rowNumber = rowNumber;
            this.header = header;
            this.dataTypes = dataTypes;
        }

        public Row build() {
            for (int columnNumber = 0; columnNumber < this.dataTypes.getValues().size(); columnNumber++) {
                this.columns.add(new Column.ColumnBuilder(rawColumns[columnNumber], columnNumber, this.header, this.dataTypes).build());
            }
            return new Row(this);
        }
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
        return new EqualsBuilder().append(this.columns, row.getColumns()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(43, 45).append(this.columns).toHashCode();
    }
}
