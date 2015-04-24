package no.sands.kodeverk.index.tasks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This task checks out a kodeverk hovedleveranse branch based on given command line arguments.
 *
 * @author Øyvind Strømmen
 */
public class Leveranse implements Task {

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
    public Leveranse withArgument(String argument) {
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

        Leveranse leveranse = (Leveranse) object;
        return new EqualsBuilder().append(argument, leveranse.getArgument()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 21).append(argument).toHashCode();
    }
}
