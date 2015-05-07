package no.sands.kodeverk.domain.content;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.content.Characters}
 *
 * @author Øyvind Strømmen
 */
public class CharactersTest {

    @Test
    public void shouldReturnAppropriateValueIfValidInputIsProvided() {
        Content content = new Characters.CharactersBuilder().rawContent("winter is coming").build();
        assertThat(content, is(notNullValue()));
        assertThat(content.getContentAsString(), is("winter is coming"));
    }

    @Test
    public void shouldHaveContentNullWhenNullIsPassed() {
        Content content = new Characters.CharactersBuilder().rawContent(null).build();
        assertThat(content, is(notNullValue()));
        assertThat(content.getContentAsString(), is(nullValue()));
    }
}