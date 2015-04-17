package no.sands.kodeverk.commandline;

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
 * @author Øyvind Strømmen
 */
public class KodeverkArgumentParser {

    private static final String L = "l";
    private static final String LEVERANSE = "leveranse";
    private static final String H = "h";
    private static final String HELP = "help";

    private static final ImmutableSet<Option> KODEVERK_OPTIONS = ImmutableSet.of(
            new KodeverkOptionBuilder()
                    .withArg(L)
                    .withLongOpt(LEVERANSE)
                    .withDescription("the leveranse which should be updated")
                    .hasArg()
                    .build(),

            new KodeverkOptionBuilder()
                    .withArg(H)
                    .withLongOpt(HELP)
                    .withDescription("print this message")
                    .hasArg()
                    .build()
    );

    private static final Options OPTIONS = new Options();

    static {
        for (Option option : KODEVERK_OPTIONS) {
            OPTIONS.addOption(option);
        }
    }

    private static final Logger LOGGER = Logger.getLogger(KodeverkArgumentParser.class);

    public static KodeverkArgument parseArguments(String [] args) {

        CommandLineParser parser = new BasicParser();

        try {
            CommandLine commandLine = parser.parse(OPTIONS, args);

            if (commandLine.hasOption(L) || commandLine.hasOption(LEVERANSE)) {
                return KodeverkArgument.LEVERANSE;
            }

            if (commandLine.hasOption(H) || commandLine.hasOption(HELP)) {
                return KodeverkArgument.HELP;
            }

        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Unable to parse command line arguments", e);
            fail();
        }
        return null;
    }

    private static void fail() {
        new HelpFormatter().printHelp("Kodeverk", OPTIONS);
        System.exit(0);
    }
}
