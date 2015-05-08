package no.sands.kodeverk.domain.content;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.content.Date}
 *
 * @author Øyvind Strømmen
 */
public class DateTest {

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenInvalidDateIsProvided() {
        new Date.DateBuilder().rawContent("winter is coming").build();
    }

    @Test
    public void shouldSetContentToNullWhenNullDateIsProvided() {
        Content content = new Date.DateBuilder().rawContent(null).build();
        assertThat(content.getContentAsString(), is(nullValue()));
    }

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenDateInInvalidFormatIsProvided() {
        new Date.DateBuilder().rawContent("2006/11/29").build();
    }

    @Test
    public void shouldSetAppropriateContentWhenDateInValidDateFormatIsProvided() {
        Content content = new Date.DateBuilder().rawContent("1960-05-05").build();
        assertThat(content.getContentAsString(), is("1960-05-05"));
    }

    @Test
    public void shouldSetContentToNullWhenBlankDateIsProvided() {
        Content content = new Date.DateBuilder().rawContent("").build();
        assertThat(content.getContentAsString(), is(""));
    }
}