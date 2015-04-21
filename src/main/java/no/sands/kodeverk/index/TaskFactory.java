package no.sands.kodeverk.index;

import java.util.EnumMap;
import java.util.Map;

import no.sands.kodeverk.commandline.KodeverkOption;
import no.sands.kodeverk.exceptions.KodeverkUnrecoverableException;
import no.sands.kodeverk.index.tasks.Task;

/**
 * Factory in charge of creating executable tasks. Available tasks are contained whitin an internal registry populated from the outside.
 *
 * @author Øyvind Strømmen
 */
public class TaskFactory {

    private static Map<KodeverkOption, Task> registeredTasks = new EnumMap<KodeverkOption, Task>(KodeverkOption.class);

    /**
     * Register a task to the factory. A task must be registered here for it to be eligable for creation.
     *
     * @param option the {@link no.sands.kodeverk.commandline.KodeverkOption}
     * @param task the {@link no.sands.kodeverk.index.tasks.Task}
     */
    public static void registerTask(KodeverkOption option, Task task) {
        registeredTasks.put(option, task);
    }

    /**
     * Creates a {@link no.sands.kodeverk.index.tasks.Task} based on given command line options. Only tasks registered using
     * {@link no.sands.kodeverk.index.TaskFactory#registerTask(no.sands.kodeverk.commandline.KodeverkOption, no.sands.kodeverk.index.tasks.Task)} will be available for creation.
     * As of right now the factory does not support multible valid command line options. In such cases, only the first valid command line option will be considered.
     *
     * @param commandLineOptions parsed command line options and their arguments
     * @return an instance of an appropiate task
     */
    public static Task createTask(Map<KodeverkOption, String> commandLineOptions) {
        if (commandLineOptions != null) {
            for (Map.Entry<KodeverkOption, String> entry : commandLineOptions.entrySet()) {

                Task task = registeredTasks.get(entry.getKey());
                if (task != null) {
                    return task.withArgument(entry.getValue());
                }
                throw new KodeverkUnrecoverableException("Could not find task '" + entry.getKey().name() + "'. If the task exists, it might not be registered with the task factory");
            }
        }
        return null;
    }
}
