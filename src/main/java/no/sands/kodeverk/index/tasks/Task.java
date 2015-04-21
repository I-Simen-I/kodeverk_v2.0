package no.sands.kodeverk.index.tasks;

/**
 * An executable task. Supported command line options are mapped to an appropiate task via {@link no.sands.kodeverk.index.TaskFactory}.
 *
 * @author Øyvind Strømmen
 */
public interface Task {

    /**
     * Execute this task.
     */
    void execute();

    /**
     * Builder method for supplying an argument to the task.
     *
     * @param argument the argument
     * @return an instance of the task
     */
    Task withArgument(String argument);

    /**
     * Return the task argument.
     *
     * @return the argument
     */
    String getArgument();
}
