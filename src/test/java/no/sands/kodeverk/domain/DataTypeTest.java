package no.sands.kodeverk.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

import no.sands.kodeverk.domain.DataType;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.DataType}
 *
 * @author Øyvind Strømmen
 */
public class DataTypeTest {

    @Test
    public void shouldReturnCharacterWhenValidStringIsPassed() {
        assertThat(DataType.getType("c"), is(DataType.CHARACTERS));
    }

    @Test
    public void shouldReturnCharacterWhenValidStringWithNumberIsPassed() {
        assertThat(DataType.getType("c42"), is(DataType.CHARACTERS));
    }

    @Test
    public void shouldReturnNullWhenInvalidNumberStringIsPassed() {
        assertThat(DataType.getType("t3b"), is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenInvalidStringIsPassed() {
        assertThat(DataType.getType("x"), is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenNullIsPassed() {
        assertThat(DataType.getType(null), is(nullValue()));
    }
}