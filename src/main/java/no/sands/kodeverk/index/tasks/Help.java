package no.sands.kodeverk.index.tasks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This task prints a help message showing available command line options and their usages.
 *
 * @author �yvind Str�mmen
 */
public class Help implements Task {

    private String argument;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Help withArgument(String argument) {
        this.argument = argument;
        return this;
    }

    /**
     * {@inheritDoc}
     */
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

        Help help = (Help) object;
        return new EqualsBuilder().append(argument, help.getArgument()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(23, 47).append(argument).toHashCode();
    }
}
