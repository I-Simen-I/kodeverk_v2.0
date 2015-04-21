package no.sands.kodeverk.index.tasks;

/**
 * This task prints a help message showing available command line options and their usages.
 *
 * @author Øyvind Strømmen
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
}
