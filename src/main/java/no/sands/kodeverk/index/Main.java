package no.sands.kodeverk.index;

import no.sands.kodeverk.commandline.KodeverkOption;
import no.sands.kodeverk.commandline.KodeverkOptionsParser;
import no.sands.kodeverk.index.tasks.Help;
import no.sands.kodeverk.index.tasks.Leveranse;

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
    }

    public static void main(String [] args) {
        TaskFactory.createTask(KodeverkOptionsParser.parseOptions(args)).execute();
    }
}
