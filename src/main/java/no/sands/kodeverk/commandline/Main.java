package no.sands.kodeverk.commandline;

import java.util.Map;

/**
 * @author �yvind Str�mmen
 */
public class Main {

    public static void main(String [] args) {
        Map<KodeverkOption, String> commandLineOptions = KodeverkOptionsParser.parseOptions(args);
    }
}
