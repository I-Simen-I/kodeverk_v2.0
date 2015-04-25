package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author Øyvind Strømmen
 */
public class DateTest {

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenInvalidDateIsProvided() {
        new Date().withRawContent("winter is coming");
    }

    @Test
    public void shouldSetContentToNullWhenNullDateIsProvided() {
        Content content = new Date().withRawContent(null);
        assertThat(content.getContentAsString(), is(nullValue()));
    }

    @Test
    public void shouldSetContentWhenValidTimestampIsProvided() {
        Content content = new Date().withRawContent("2006/11/29");
        assertThat(content.getContentAsString(), is("29.11.2006"));
    }
}