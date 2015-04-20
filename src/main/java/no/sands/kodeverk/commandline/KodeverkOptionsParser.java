package no.sands.kodeverk.commandline;

import java.util.EnumMap;
import java.util.Map;

import com.google.common.collect.ImmutableSet;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
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

    private static final String L = "l";
    private static final String LEVERANSE = "leveranse";
    private static final String H = "h";
    private static final String HELP = "help";
    private static final String ARG = "argument";

    /**
     * These are the command line options supported by the application.
     */
    private static final ImmutableSet<Option> KODEVERK_OPTIONS = ImmutableSet.of(
            new KodeverkOptionBuilder()
                    .withOption(L)
                    .withArgName(ARG)
                    .withLongOpt(LEVERANSE)
                    .withDescription("the leveranse which should be updated")
                    .hasArg()
                    .build(),

            new KodeverkOptionBuilder()
                    .withOption(H)
                    .withLongOpt(HELP)
                    .withDescription("print this message")
                    .build()
    );

    private static final Options OPTIONS = new Options();

    static {
        for (Option option : KODEVERK_OPTIONS) {
            OPTIONS.addOption(option);
        }
    }

    /**
     * Parse commandline options ({@link no.sands.kodeverk.commandline.KodeverkOption}) and map them to their supplied arguments if any
     * exists. If unsupported options are provided the program will print a help message before it terminates.
     *
     * @param args the command line arguments
     * @return a map between options and arguments ({@code Map<KodeverkOption, String>})
     */
    public static Map<KodeverkOption, String> parseOptions(String[] args) {
        Map<KodeverkOption, String> kodeverkOptions = new EnumMap<KodeverkOption, String>(KodeverkOption.class);

        CommandLineParser parser = new BasicParser();
        try {
            CommandLine commandLine = parser.parse(OPTIONS, args);

            if (commandLine.hasOption(L)) {
                kodeverkOptions.put(KodeverkOption.LEVERANSE, commandLine.getOptionValue(L));
            }

            if (commandLine.hasOption(H)) {
                kodeverkOptions.put(KodeverkOption.HELP, null);
            }
            return kodeverkOptions;

        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Unable to parse command line arguments", e);
            fail();
        }
        return new EnumMap<KodeverkOption, String>(KodeverkOption.class);
    }

    /**
     * Print a help message then terminate the program.
     */
    private static void fail() {
        new HelpFormatter().printHelp("kodeverk", OPTIONS);
        System.exit(0);
    }
}
