package no.sands.kodeverk.commandline;

import java.util.Map;

/**
 * @author Øyvind Strømmen
 */
public class Main {

    public static void main(String [] args) {
        Map<KodeverkOption, String> commandLineOptions = KodeverkOptionsParser.parseOptions(args);
    }
}
