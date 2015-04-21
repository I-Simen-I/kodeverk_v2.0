package no.sands.kodeverk.index.tasks;

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
}
