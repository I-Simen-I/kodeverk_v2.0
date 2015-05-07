package no.sands.kodeverk.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import no.sands.kodeverk.domain.HeaderType;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.HeaderType}
 *
 * @author �yvind Str�mmen
 */
public class HeaderTypeTest {

    @Test
    public void shouldReturnDatoEndretWhenValidArgumentIsPassed() {
        assertThat(HeaderType.getType("dato_endret"), is(HeaderType.DATO_ENDRET));
    }

    @Test
    public void shouldReturnNullWhenInvalidArgumentIsPassed() {
        assertThat(HeaderType.getType("winter is coming"), is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenNullIsPassed() {
        assertThat(HeaderType.getType(null), is(nullValue()));
    }
}