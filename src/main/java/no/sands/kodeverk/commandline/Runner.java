package no.sands.kodeverk.commandline;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;

/**
 * @author Øyvind Strømmen
 */
public class Runner {

    public static void betterRun() throws Exception {
        CommandLine commandLine = new CommandLine("git");
        commandLine.addArgument("status");

        DefaultExecutor executor = new DefaultExecutor();
        executor.setWatchdog(new ExecuteWatchdog(30000));
        executor.setExitValue(1);

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        executor.execute(commandLine, resultHandler);
        resultHandler.waitFor();
    }
}
