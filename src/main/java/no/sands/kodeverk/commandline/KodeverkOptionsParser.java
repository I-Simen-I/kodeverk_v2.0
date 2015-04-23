package no.sands.kodeverk.commandline;

import java.util.EnumMap;
import java.util.Map;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This class defines which command line options should be supported by the application. The class is also
 * in charge of parsing command line options and their arguments. Any unsupported options recieved will cause the
 * application to terminate.
 *
 * @author Øyvind Strømmen
 */
public class KodeverkOptionsParser {

    private static final Logger LOGGER = Logger.getLogger(KodeverkOptionsParser.class);

    private static final Options OPTIONS = new Options();

    static {
        for (KodeverkOption kodeverkOption : KodeverkOption.values()) {
            OPTIONS.addOption(
                    new KodeverkOptionBuilder()
                            .withOption(kodeverkOption.getOption())
                            .withLongOpt(kodeverkOption.getLongOption())
                            .withArgName(kodeverkOption.getArgName())
                            .withDescription(kodeverkOption.getDescription())
                            .build()
            );
        }
    }

    /**
     * Parse commandline options ({@link no.sands.kodeverk.commandline.KodeverkOption}) and map them to their supplied arguments if any
     * exists. If unsupported options are provided the program will print a help message before it terminates.
     *
     * @param args the command line arguments
     * @return a map between options and arguments ({@code KodeverkOption} and {@code String} )
     */
    public static Map<KodeverkOption, String> parseOptions(String[] args) {
        Map<KodeverkOption, String> kodeverkOptions = new EnumMap<>(KodeverkOption.class);

        CommandLineParser parser = new BasicParser();
        try {
            CommandLine commandLine = parser.parse(OPTIONS, args);

            for (KodeverkOption kodeverkOption : KodeverkOption.values()) {
                if (commandLine.hasOption(kodeverkOption.getOption())) {
                    kodeverkOptions.put(kodeverkOption, commandLine.getOptionValue(kodeverkOption.getOption()));
                }
            }
            return kodeverkOptions;
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Unable to parse command line arguments", e);
            fail();
        }
        return new EnumMap<>(KodeverkOption.class);
    }

    /**
     * Print a help message then terminate the program.
     */
    private static void fail() {
        new HelpFormatter().printHelp("kodeverk", OPTIONS);
        System.exit(0);
    }
}
