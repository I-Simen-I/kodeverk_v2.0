package no.sands.kodeverk.domain.content;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.content.Timestamp}
 *
 * @author Øyvind Strømmen
 * @author Simen Søhol
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
    public void shouldSetAppropriateContentWhenAValidTimestampIsProvided() {
        Content contentTimestampFormat1 = new Timestamp.TimeStampBuilder().rawContent("2015-12-10 23:00").build();
        Content contentTimestampFormat2 = new Timestamp.TimeStampBuilder().rawContent("10.12.2015 23:00").build();

        assertThat(contentTimestampFormat1.getContentAsString(), is("2015-12-10 23:00"));
        assertThat(contentTimestampFormat2.getContentAsString(), is("10.12.2015 23:00"));
    }

    @Test(expected = KodeverkInvalidContentException.class)
    public void shouldFailWhenTimestampInInvalidFormatIsProvided() {
        new Timestamp.TimeStampBuilder().rawContent("2006/11/29 17:00").build();
    }
}