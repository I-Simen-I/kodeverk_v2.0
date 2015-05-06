package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author Øyvind Strømmen
 */
public class TimestampTest {

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenInvalidTimestampIsProvided() {
        new Timestamp.TimeStampBuilder().rawContent("winter is coming").build();
    }

    @Test
    public void shouldSetContentToNullWhenNullTimestampIsProvided() {
        Content content = new Timestamp.TimeStampBuilder().rawContent(null).build();
        assertThat(content.getContentAsString(), is(nullValue()));
    }

    @Test
    public void shouldSetContentWhenValidTimestampIsProvided() {
        Content content = new Timestamp.TimeStampBuilder().rawContent("2006/11/29 17:00").build();
        assertThat(content.getContentAsString(), is("2006-11-29 17:00"));
    }
}