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

        private List<Column> columns = new ArrayList<>();

        public RowBuilder(String [] rawColumns, int rowNumber) {
            this.rawColumns = rawColumns;
            this.rowNumber = rowNumber;
        }

        public Row build() {
            Row row = new Row(this);
            for (int columnNumber = 0; columnNumber < rawColumns.length; columnNumber++) {
                this.columns.add(new Column.ColumnBuilder(DataType.TIMESTAMP, rawColumns[columnNumber], columnNumber, row).build());
            }
            return row;
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
