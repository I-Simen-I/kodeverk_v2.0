package no.sands.kodeverk.index;

import no.sands.kodeverk.commandline.KodeverkOption;
import no.sands.kodeverk.commandline.KodeverkOptionsParser;
import no.sands.kodeverk.index.tasks.Generate;
import no.sands.kodeverk.index.tasks.GroupedTask;
import no.sands.kodeverk.index.tasks.Help;
import no.sands.kodeverk.index.tasks.Leveranse;
import no.sands.kodeverk.index.tasks.Task;
import no.sands.kodeverk.index.tasks.Validate;

/**
 * @author Øyvind Strømmen
 */
public class Main {

    /**
     * Register tasks with the {@link no.sands.kodeverk.index.TaskFactory}
     */
    static {
        TaskFactory.registerTask(KodeverkOption.LEVERANSE, new Leveranse());
        TaskFactory.registerTask(KodeverkOption.HELP, new Help());
        TaskFactory.registerTask(KodeverkOption.GENERATE, new GroupedTask().with(new Validate()).with(new Generate()));
    }

    public static void main(String [] args) {
        for (Task task : TaskFactory.createTasks(KodeverkOptionsParser.parseOptions(args))) {
            task.execute();
        }
    }
}
