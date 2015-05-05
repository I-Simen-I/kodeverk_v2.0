package no.sands.kodeverk.converter.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Øyvind Strømmen
 */
public class Column {

    private final Content content;

    private final int colummNumber;

    private final Row row;

    private Column(ColumnBuilder builder) {
        this.content = builder.content;
        this.colummNumber = builder.columnNumber;
        this.row = builder.row;
    }

    public Content getContent() {
        return content;
    }

    public int getColummNumber() {
        return colummNumber;
    }

    public Row getRow() {
        return row;
    }

    public static class ColumnBuilder {

        private final DataType dataType;

        private final String rawContent;

        private final int columnNumber;

        private final Row row;

        private Content content;

        public ColumnBuilder(DataType dataType, String rawContent, int columnNumber, Row row) {
            this.dataType = dataType;
            this.rawContent = rawContent;
            this.columnNumber = columnNumber;
            this.row = row;
        }

        public Column build() {
            content = ContentFactory.createContent(dataType, rawContent);
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
