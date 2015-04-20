package no.sands.kodeverk.commandline;

import org.apache.commons.cli.Option;

/**
 * Convenience builder for {@link org.apache.commons.cli.Option}. Replaces Commons cli's own builder
 *
 * @see org.apache.commons.cli.Option
 * @see org.apache.commons.cli.OptionBuilder
 *
 * @author Øyvind Strømmen
 */
public class KodeverkOptionBuilder {

    private String option;

    private String argName;

    private String longOpt;

    private String description;

    private boolean hasArg;

    public KodeverkOptionBuilder() {
        option = null;
        argName = null;
        longOpt = null;
        description = null;
        hasArg = false;
    }

    public KodeverkOptionBuilder withOption(String option) {
        this.option = option;
        return this;
    }

    public KodeverkOptionBuilder withArgName(String argName) {
        this.argName = argName;
        return this;
    }

    public KodeverkOptionBuilder withLongOpt(String longOpt) {
        this.longOpt = longOpt;
        return this;
    }

    public KodeverkOptionBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public KodeverkOptionBuilder hasArg() {
        this.hasArg = true;
        return this;
    }

    public Option build() {
        Option buildtOption = new Option(option, description);
        buildtOption.setArgName(argName);
        buildtOption.setLongOpt(longOpt);
        buildtOption.setArgs(hasArg ? 1 : 0);
        return buildtOption;
    }
}
