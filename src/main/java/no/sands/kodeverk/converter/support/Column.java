package no.sands.kodeverk.converter.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author �yvind Str�mmen
 */
public class Column {

    private Content content;

    public Column(DataType dataType, String rawContent) {
        content = ContentFactory.createContent(dataType, rawContent);
    }

    public Content getContent() {
        return content;
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
        return new EqualsBuilder().append(content, column.getContent()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(65, 3).append(content).toHashCode();
    }
}
