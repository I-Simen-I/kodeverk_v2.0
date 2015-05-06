package no.sands.kodeverk.converter.support;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.DataTypes}
 *
 * @author Øyvind Strømmen
 */
@RunWith(MockitoJUnitRunner.class)
public class DataTypesTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Header header;

    private static final String[] VALID_HEADER = new String[] {
            "id", "dato_fom", "RANDOM", "dato_tom", "er_gyldig", "opprettet_av", "dato_opprettet", "endret_av", "dato_endret", "ignore"
    };

    private static final String[] VALID_DATA_TYPES = new String[] {
            "i", "d", "c", "d", "c", "c", "t", "c", "t", "", ""
    };

    private static final String[] VALID_DATA_TYPES_WITH_NUMBERS = new String[] {
            "i", "d1", "c1", "d2", "c2", "c3", "t1", "c4", "t2"
    };

    private static final String[] CONTAINS_INVALID_DATA_TYPE = new String[] {
            "i", "d", "c", "x", "c", "c", "t", "c", "t"
    };

    private static final String[] CONTAINS_A_DATA_TYPE_NOT_MATCHING_HEADER = new String[] {
            "i", "d", "c", "d", "d", "c", "t", "c", "t"
    };

    private static final String[] CONTAINS_INVALID_FIRST_COLUMN_TYPE = new String[] {
            "d", "d", "c", "d", "c", "c", "t", "c", "t"
    };

    private static final String[] CONTAINS_NON_CONTINIOUS_TYPES = new String[] {
            "c", "d", "c", "d", "", "c", "t", "c", "t"
    };

    private static final String[] CONTAINS_BLANK_FIRST_VALUE = new String[] {
            "", "d", "c", "d", "", "c", "t", "c", "t"
    };

    @Test
    public void shouldFailWhenDataTypeDoesntMatchHeader() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.DATATYPE_DOESNT_MATCH_HEADER);
        new DataTypes.DataTypesBuilder(CONTAINS_A_DATA_TYPE_NOT_MATCHING_HEADER, mockHeader(VALID_HEADER)).build();
    }

    @Test
    public void shouldFailWhenFirstColumnIsNeitherCNorI() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.INVALID_FIRST_COLUMN);
        new DataTypes.DataTypesBuilder(CONTAINS_INVALID_FIRST_COLUMN_TYPE, mockHeader(VALID_HEADER)).build();
    }

    @Test
    public void shouldFailWhenFirstColumnIsBlank() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.INVALID_FIRST_COLUMN);
        new DataTypes.DataTypesBuilder(CONTAINS_BLANK_FIRST_VALUE, mockHeader(VALID_HEADER)).build();
    }

    @Test
    public void shouldFailWhenDataTypesContainsInvalidDataType() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.INVALID_DATA_TYPE);
        new DataTypes.DataTypesBuilder(CONTAINS_INVALID_DATA_TYPE, mockHeader(VALID_HEADER)).build();
    }

    @Test
    public void shouldCreateAppropriateValuesWhenValidTypesArePassed() {
        DataTypes dataTypes = new DataTypes.DataTypesBuilder(VALID_DATA_TYPES, mockHeader(VALID_HEADER)).build();
        assertThat(dataTypes.getValues(), is(Arrays.asList(VALID_DATA_TYPES).subList(0, 9)));
    }

    @Test
    public void shouldCreateAppropriateValuesWhenValidTypesWithNumbersArePassed() {
        DataTypes dataTypes = new DataTypes.DataTypesBuilder(VALID_DATA_TYPES_WITH_NUMBERS, mockHeader(VALID_HEADER)).build();
        assertThat(dataTypes.getValues(), is(Arrays.asList(VALID_DATA_TYPES_WITH_NUMBERS)));
    }

    @Test
    public void shouldFailWhenDataTypesAreNonContinious() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.NON_CONTINUOUS);
        new DataTypes.DataTypesBuilder(CONTAINS_NON_CONTINIOUS_TYPES, mockHeader(VALID_HEADER)).build();
    }

    private Header mockHeader(final String[] headerValues) {
        when(header.getValues()).thenReturn(Arrays.asList(headerValues));
        return header;
    }
}