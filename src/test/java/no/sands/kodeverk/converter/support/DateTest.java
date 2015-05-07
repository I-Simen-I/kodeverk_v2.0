package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.Date}
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

    @Test
    public void shouldSetContentWhenValidTimestampIsProvided() {
        Content content = new Date.DateBuilder().rawContent("2006/11/29").build();
        assertThat(content.getContentAsString(), is("29.11.2006"));
    }

    @Test
    public void shouldSetContentToNullWhenBlankDateIsProvided() {
        Content content = new Date.DateBuilder().rawContent("").build();
        assertThat(content.getContentAsString(), is(nullValue()));
    }
}