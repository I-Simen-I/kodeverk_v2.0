package no.sands.kodeverk.converter.support;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.BeforeClass;
import org.junit.Test;

import no.sands.kodeverk.converter.support.Date.DateBuilder;
import no.sands.kodeverk.converter.support.Index.IndexBuilder;
import no.sands.kodeverk.converter.support.Timestamp.TimeStampBuilder;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.ContentFactoryTest}
 *
 * @author Øyvind Strømmen
 */
public class ContentFactoryTest {

    @BeforeClass
     public static void setUp() {
        ContentFactory.registerContent(DataType.TIMESTAMP, new TimeStampBuilder());
        ContentFactory.registerContent(DataType.DATE, new DateBuilder());
        ContentFactory.registerContent(DataType.INDEX, new IndexBuilder());
    }

    @Test
    public void shouldCreateInstanceOfTimestampWithAppropriateValue() {
        Content content = ContentFactory.createContent(DataType.TIMESTAMP, "2006/11/29 17:00");
        assertThat(content, is(notNullValue()));
        assertThat(content, is(instanceOf(Timestamp.class)));
        assertThat(content.getContentAsString(), is("2006-11-29 17:00"));
    }

    @Test
    public void shouldCreateInstanceOfDataWithAppropriateValue() {
        Content content = ContentFactory.createContent(DataType.DATE, "2006/11/29");
        assertThat(content, is(notNullValue()));
        assertThat(content, is(instanceOf(Date.class)));
        assertThat(content.getContentAsString(), is("29.11.2006"));
    }

    @Test
    public void shouldCreateInstanceOfIndexWithAppropriateValue() {
        Content content = ContentFactory.createContent(DataType.INDEX, "5");
        assertThat(content, is(notNullValue()));
        assertThat(content, is(instanceOf(Index.class)));
        assertThat(content.getContentAsString(), is("5"));
    }

    @Test
    public void shouldReturnNullIfDateTypeIsNull() {
        assertThat(ContentFactory.createContent(null, "5"), is(nullValue()));
    }
}