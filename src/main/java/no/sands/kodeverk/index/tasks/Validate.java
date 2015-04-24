package no.sands.kodeverk.index.tasks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Øyvind Strømmen
 */
public class Validate implements Task {

    private String argument;

    @Override
    public void execute() {

    }

    @Override
    public Task withArgument(String argument) {
        this.argument = argument;
        return this;
    }

    @Override
    public String getArgument() {
        return argument;
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

        Validate validate = (Validate) object;
        return new EqualsBuilder().append(argument, validate.getArgument()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 7).append(argument).toHashCode();
    }
}
