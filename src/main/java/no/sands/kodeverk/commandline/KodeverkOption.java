package no.sands.kodeverk.commandline;

/**
 * Command line options supported by the application
 *
 * @author Øyvind Strømmen
 */
public enum KodeverkOption {

    LEVERANSE("l", "leveranse", "the leveranse which should be updated", "argument"),
    HELP("h", "help", "print this message", null),
    GENERATE("gen", "generate", "generate kodeverk script", null);

    private String option;
    private String longOption;
    private String description;
    private String argName;

    private KodeverkOption(String option, String longOption, String description, String argName) {
        this.option = option;
        this.longOption = longOption;
        this.description = description;
        this.argName = argName;
    }

    public String getOption() {
        return option;
    }

    public String getLongOption() {
        return longOption;
    }

    public String getDescription() {
        return description;
    }

    public String getArgName() {
        return argName;
    }
}
