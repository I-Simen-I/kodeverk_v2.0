package no.sands.kodeverk.domain.content;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import no.sands.kodeverk.domain.content.Content;
import no.sands.kodeverk.domain.content.Index;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.content.Index}
 *
 * @author Øyvind Strømmen
 */
public class IndexTest {

    @Test
    public void shouldSetContentWhenNumericStringIsProvided() {
        Content content = new Index.IndexBuilder().rawContent("42").build();
        assertThat(content.getContentAsString(), is("42"));
    }

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenNonNumericContentIsProvided() {
        new Index.IndexBuilder().rawContent("winter is coming").build();
    }

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenNullIsProvided() {
        new Index.IndexBuilder().rawContent(null).build();
    }
}