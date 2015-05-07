package no.sands.kodeverk.converter.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A column is contained within a {@link no.sands.kodeverk.converter.support.Row} and contains {@link no.sands.kodeverk.converter.support.Content}. A Column is
 * aware of it's position within a row and the value of it's corresponding {@link no.sands.kodeverk.converter.support.Header} and {@link no.sands.kodeverk.converter.support.DataTypes} column.
 *
 * @author Øyvind Strømmen
 */
public class Column {

    private final Content content;

    private final int colummNumber;

    private Column(ColumnBuilder builder) {
        this.content = builder.content;
        this.colummNumber = builder.columnNumber;
    }

    /**
     * Get the contents of this column
     *
     * @return the content
     */
    public Content getContent() {
        return content;
    }

    /**
     * Get the position of this Column within it's {@link no.sands.kodeverk.converter.support.Row}
     *
     * @return the columnNumber
     */
    public int getColummNumber() {
        return colummNumber;
    }

    public static class ColumnBuilder {

        private final String rawContent;

        private final int columnNumber;

        private final Header header;

        private final DataTypes dataTypes;

        private Content content;

        public ColumnBuilder(String rawContent, int columnNumber, Header header, DataTypes dataTypes) {
            this.rawContent = rawContent;
            this.columnNumber = columnNumber;
            this.header = header;
            this.dataTypes = dataTypes;
        }

        /**
         * Validate the state of the builder and build a {@link no.sands.kodeverk.converter.support.Column} if valid.
         *
         * @return a Column if validation was successful
         */
        public Column build() {
            //TODO: Validate that dato_fom cannot be null
            content = ContentFactory.createContent(DataType.getType(this.dataTypes.getValues().get(columnNumber)), this.rawContent);
            return new Column(this);
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

        Column column = (Column) object;
        return new EqualsBuilder().append(this.content, column.getContent()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(65, 3).append(this.content).toHashCode();
    }
}
