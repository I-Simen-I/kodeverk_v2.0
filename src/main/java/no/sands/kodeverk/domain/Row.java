package no.sands.kodeverk.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A Row is contained within a {@link Kodeverk} and contains {@link Column}s.
 * A Row is aware of it's position within a Kodeverk.
 *
 * @author Øyvind Strømmen
 */
public class Row {

    private final List<Column> columns;

    private final int rowNumber;

    private Row(RowBuilder builder) {
        this.columns = builder.columns;
        this.rowNumber = builder.rowNumber;
    }

    /**
     * Get the Columns contained within this Row
     *
     * @return a List of Columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * Get this row's position within a {@link Kodeverk}
     *
     * @return the rowNumber
     */
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

        /**
         * Validate the state of the builder and build a {@link Row} if valid.
         *
         * @return a Row if validation was successful
         */
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
