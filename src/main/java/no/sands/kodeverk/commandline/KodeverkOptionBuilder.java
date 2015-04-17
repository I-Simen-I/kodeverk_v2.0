package no.sands.kodeverk.commandline;

import org.apache.commons.cli.Option;

/**
 * @author Øyvind Strømmen
 */
public class KodeverkOptionBuilder {

    private Option option;

    public KodeverkOptionBuilder() {
        option = new Option(null, null);
    }

    public KodeverkOptionBuilder withArg(String argName) {
        option.setArgName(argName);
        return this;
    }

    public KodeverkOptionBuilder withLongOpt(String longOpt) {
        option.setLongOpt(longOpt);
        return this;
    }

    public KodeverkOptionBuilder withDescription(String description) {
        option.setDescription(description);
        return this;
    }

    public KodeverkOptionBuilder hasArg() {
        option.setArgs(1);
        return this;
    }

    public Option build() {
        return option;
    }
}
