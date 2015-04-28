package no.sands.kodeverk.converter.support;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.DataTypes}
 *
 * @author Øyvind Strømmen
 */
public class DataTypesTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final String [] VALID_HEADER = new String[]{"id", "dato_fom", "RANDOM", "dato_tom", "er_gyldig", "opprettet_av", "dato_opprettet", "endret_av", "dato_endret"};
    private static final String [] VALID_DATA_TYPES = new String[]{"i", "d", "c", "d", "c", "c", "t", "c", "t", "", ""};
    private static final String [] CONTAINS_INVALID_DATA_TYPE = new String[]{"i", "d", "c", "x", "c", "c", "t", "c", "t"};
    private static final String [] INVALID_DATA_TYPES = new String[]{"i", "d", "c", "d", "d", "c", "t", "c", "t"};
    private static final String [] DATA_TYPES_MISSING_INDEX = new String[]{"d", "d", "c", "d", "c", "c", "t", "c", "t"};

    @Test
    public void shouldFailWhenDataTypeDoesntMatchHeader() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.DATATYPE_DOESNT_MATCH_HEADER);

        Kodeverk kodeverk = createKodeverk(createHeader(VALID_HEADER));
        new DataTypes().withKodeverk(kodeverk).withRawValues(INVALID_DATA_TYPES);
    }

    @Test
    public void shouldFailWhenDataTypesIsMissingHeader() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_INDEX);

        Kodeverk kodeverk = createKodeverk(createHeader(VALID_HEADER));
        new DataTypes().withKodeverk(kodeverk).withRawValues(DATA_TYPES_MISSING_INDEX);
    }

    @Test
    public void shouldFailWhenDataTypesContainsInvalidDataType() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.INVALID_DATA_TYPE);

        Kodeverk kodeverk = createKodeverk(createHeader(VALID_HEADER));
        new DataTypes().withKodeverk(kodeverk).withRawValues(CONTAINS_INVALID_DATA_TYPE);
    }

    @Test
    public void shouldCreateAppropriateValuesWhenValidTypesArePassed() {
        Kodeverk kodeverk = createKodeverk(createHeader(VALID_HEADER));
        DataTypes dataTypes = new DataTypes().withKodeverk(kodeverk).withRawValues(VALID_DATA_TYPES);
        assertThat(dataTypes.getValues(), is(Arrays.asList(VALID_DATA_TYPES).subList(0,9)));
    }

    private static Header createHeader(final String[] headerValues) {
        return new Header() {
            @Override
            public List<String> getValues() {
                return Arrays.asList(headerValues);
            }
        };
    }

    private static Kodeverk createKodeverk(final Header header) {
        return new Kodeverk() {
            @Override
            public Header getHeader() {
                return header;
            }
        };
    }
}