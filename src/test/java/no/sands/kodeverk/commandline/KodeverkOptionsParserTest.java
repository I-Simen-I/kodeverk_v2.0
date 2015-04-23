package no.sands.kodeverk.commandline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Unit tests for {@link KodeverkOptionsParser}
 *
 * @author Øyvind Strømmen
 */
public class KodeverkOptionsParserTest {

    @Rule
    public ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithStatus0WhenNoArgumentsAreProvidedToL() {
        exit.expectSystemExitWithStatus(0);
        KodeverkOptionsParser.parseOptions(new String[] {"-l"});
    }

    @Test
    public void shouldReturnLeveranseWhenLIsProvidedAValidArgument() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"-l", "2015/HL3"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.LEVERANSE, "2015/HL3"));
    }

    @Test
    public void shouldExitWithStatus0WhenNoArgumentsAreProvidedToLeveranse() {
        exit.expectSystemExitWithStatus(0);
        KodeverkOptionsParser.parseOptions(new String[] {"--leveranse"});
    }

    @Test
    public void shouldReturnLeveranseWhenLeveranseIsProvidedAValidArgument() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"--leveranse", "2015/HL3"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.LEVERANSE, "2015/HL3"));

    }

    @Test
    public void shouldReturnHelpWhenHIsPassedAsArgument() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"-h"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.HELP, null));
    }

    @Test
    public void shouldReturnHelpWhenHWithArgumentsIsPassedAsArguments() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"-h", "2015/HL3"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.HELP, null));
    }

    @Test
    public void shouldReturnHelpWhenHelpIsPassedAsArgument() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"--help"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.HELP, null));
    }

    @Test
    public void shouldReturnEmptyMapWhenNoOptionsAreProvided() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {});
        assertThat(options.entrySet(), hasSize(0));
    }

    @Test
    public void shouldReturnHelpAndLeveranseWhenLeveranseAndHelpIsPassedAsParameters() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"--help", "-l", "2015/HL3", "--generate"});
        assertThat(options.entrySet(), hasSize(3));
        assertThat(options, hasEntry(KodeverkOption.HELP, null));
        assertThat(options, hasEntry(KodeverkOption.LEVERANSE, "2015/HL3"));
        assertThat(options, hasEntry(KodeverkOption.GENERATE, null));
    }

    @Test
    public void shouldExitWithStatus0WhenSeveralArgumentsAreProvidedButNoneForL() {
        exit.expectSystemExitWithStatus(0);
        KodeverkOptionsParser.parseOptions(new String[] {"--help", "2015/HL3", "-l"});
    }

    @Test
    public void shouldReturnOneHelpWhenHelpTimesTwoIsPassedAsArguments() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"-h", "--help"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.HELP, null));
    }

    @Test
    public void shouldReturnEmptyListWhenNoValidArgumentsAreProvided() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"winter is coming"});
        assertThat(options.entrySet(), hasSize(0));
    }

    @Test
    public void shouldExitWithStatus0WhenUnrecognizedOptionIsProvided() {
        exit.expectSystemExitWithStatus(0);
        KodeverkOptionsParser.parseOptions(new String[] {"-x", "55"});
    }

    @Test
    public void shouldReturnGenerateWhenGenerateIsPassedAsOption() {
        Map<KodeverkOption, String> options = KodeverkOptionsParser.parseOptions(new String[] {"-gen"});
        assertThat(options.entrySet(), hasSize(1));
        assertThat(options, hasEntry(KodeverkOption.GENERATE, null));
    }
}