package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author �yvind Str�mmen
 */
public class TimestampTest {

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenInvalidTimestampIsProvided() {
        new Timestamp().withRawContent("winter is coming");
    }

    @Test
    public void shouldSetContentToNullWhenNullTimestampIsProvided() {
        Content content = new Timestamp().withRawContent(null);
        assertThat(content.getContentAsString(), is(nullValue()));
    }

    @Test
    public void shouldSetContentWhenValidTimestampIsProvided() {
        Content content = new Timestamp().withRawContent("2006/11/29 17:00");
        assertThat(content.getContentAsString(), is("2006-11-29 17:00"));
    }
}