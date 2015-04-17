package no.sands.kodeverk.commandline;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author Øyvind Strømmen
 */
public class Main {

    public static void main(String [] args) {

        Options options = new Options();
        options.addOption(new KodeverkOptionBuilder()
                .withArg("l")
                .withLongOpt("leveranse")
                .withDescription("the leveranse which should be updated")
                .hasArg()
                .build());

        CommandLineParser parser = new BasicParser();


        try {

            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("l")) {

            }

            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("kodeverk", options);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return;
    }
}
