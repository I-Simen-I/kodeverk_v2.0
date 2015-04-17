package no.sands.kodeverk.commandline;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 * Unit tests for {@link no.sands.kodeverk.commandline.KodeverkArgumentParser}
 *
 * @author Øyvind Strømmen
 */
public class KodeverkArgumentParserTest {

    @Test
    public void shouldReturnLeveranseWhenLIsPassedAsArgument() {
        assertThat(KodeverkArgumentParser.parseArguments(new String[]{"-l"}), is(KodeverkArgument.LEVERANSE));
    }

    @Test
    public void shouldReturnLeveranseWhenLeveranseIsPassedAsArgument() {
        assertThat(KodeverkArgumentParser.parseArguments(new String[]{"--leveranse"}), is(KodeverkArgument.LEVERANSE));
    }

    @Test
    public void shouldReturnHelpWhenhIsPassedAsArgument() {
        assertThat(KodeverkArgumentParser.parseArguments(new String[]{"-h"}), is(KodeverkArgument.HELP));
    }

    @Test
    public void shouldReturnHelpWhenHelpIsPassedAsArgument() {
        assertThat(KodeverkArgumentParser.parseArguments(new String[]{"--help"}), is(KodeverkArgument.HELP));
    }

    @Test
    public void should() {
        KodeverkArgumentParser.parseArguments(new String[]{});
    }

    @Test
    public void testTest() {
        KodeverkArgumentParser.parseArguments(new String[]{"-h", "--help"});
        KodeverkArgumentParser.parseArguments(new String[]{"-h", "winter is coming"});
        KodeverkArgumentParser.parseArguments(new String[]{"winter is coming"});
        KodeverkArgumentParser.parseArguments(new String[]{"-h", "-leveranse"});
    }
}