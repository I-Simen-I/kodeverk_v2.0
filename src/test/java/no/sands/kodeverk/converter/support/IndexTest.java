package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.Index}
 *
 * @author Øyvind Strømmen
 */
public class IndexTest {

    @Test
    public void shouldSetContentWhenNumericStringIsProvided() {
        Content content = new Index().withRawContent("42");
        assertThat(content.getContentAsString(), is("42"));
    }

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenNonNumericContentIsProvided() {
        new Index().withRawContent("winter is coming");
    }

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenNullIsProvided() {
        new Index().withRawContent(null);
    }
}