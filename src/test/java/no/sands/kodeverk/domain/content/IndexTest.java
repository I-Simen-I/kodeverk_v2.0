package no.sands.kodeverk.domain.content;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.content.Index}
 *
 * @author Øyvind Strømmen
 * @author Simen Søhol
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

    @Test
    public void shouldReturnNull() {
        Content content = new Index.IndexBuilder().rawContent(null).build();
        assertThat(content.getContentAsString(), is(nullValue()));
    }

    @Test
    public void shouldReturnBlankWhenInputIsBlank() {
        Content content = new Index.IndexBuilder().rawContent("").build();
        assertThat(content.getContentAsString(), is(""));
    }
}