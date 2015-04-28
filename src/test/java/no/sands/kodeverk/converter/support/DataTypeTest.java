package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.DataType}
 *
 * @author Øyvind Strømmen
 */
public class DataTypeTest {

    @Test
    public void shouldReturnCharacterWhenValidStringIsPassed() {
        assertThat(DataType.getType("c"), is(DataType.CHARACTERS));
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