package no.sands.kodeverk.converter.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Øyvind Strømmen
 */
public class Column {

    private final Content content;

    private final int colummNumber;

    private Column(ColumnBuilder builder) {
        this.content = builder.content;
        this.colummNumber = builder.columnNumber;
    }

    public Content getContent() {
        return content;
    }

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

        public Column build() {
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
