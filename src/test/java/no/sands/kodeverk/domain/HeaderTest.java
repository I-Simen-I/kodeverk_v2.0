package no.sands.kodeverk.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.domain.Header;
import no.sands.kodeverk.domain.HeaderType;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.domain.Header}
 *
 * @author �yvind Str�mmen
 */
public class HeaderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFailWhenHeaderIsEmpty() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_FIELDS);
        new Header.HeaderBuilder(new String[]{}).build();
    }

    @Test
    public void shouldFailWhenHeaderContainsNullValues() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_FIELDS);
        new Header.HeaderBuilder(new String[]{"dato_endret", null}).build();
    }

    @Test
    public void shouldFailWhenHeaderContainsDuplicates() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.DUPLICATE);

        List<String> rawValues = HeaderType.getHeaderNames();
        rawValues.addAll(HeaderType.DATO_ENDRET.getValidHeaderNames());

        String[] rawArray = new String[rawValues.size()];
        new Header.HeaderBuilder(rawValues.toArray(rawArray)).build();
    }

    @Test
    public void shouldFailWhenOnlyRequiredFieldsArePresent() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_FIELDS);

        List<String> rawValues = HeaderType.getHeaderNames();

        String[] rawArray = new String[rawValues.size()];
        new Header.HeaderBuilder(rawValues.toArray(rawArray)).build();
    }

    @Test
    public void shouldCreateAppropriateValuesWhenRequiredFieldsArePresent() {
        List<String> rawValues = HeaderType.getHeaderNames();
        rawValues.add("this field is an id");

        String[] rawArray = new String[rawValues.size()];

        Header header = new Header.HeaderBuilder(rawValues.toArray(rawArray)).build();
        assertThat(header.getValues(), is(rawValues));
    }

    @Test
    public void shouldFailWhenOneOreMoreFieldsAreMoreThan30Characters() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.EXCEEDED_CHAR_LIMIT);

        List<String> rawValues = HeaderType.getHeaderNames();
        rawValues.add("12345-12345-12345-12345-12345-12345");

        String[] rawArray = new String[rawValues.size()];
        new Header.HeaderBuilder(rawValues.toArray(rawArray)).build();

    }

    @Test
    public void shouldFailWhenFieldsAreNonContinuous() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.NON_CONTINUOUS);

        List<String> rawValues = HeaderType.getHeaderNames();
        rawValues.add(null);
        rawValues.add("a_column");

        String[] rawArray = new String[rawValues.size()];
        new Header.HeaderBuilder(rawValues.toArray(rawArray)).build();
    }

    @Test
    public void shouldCreateAppropriateValuesWhenValidValuesAreFollowedByNullValues() {
        List<String> rawValues = HeaderType.getHeaderNames();
        rawValues.add("this field is an id");

        List<String> expectedValues = new ArrayList<>(rawValues);

        rawValues.add(null);
        rawValues.add(null);

        String[] rawArray = new String[rawValues.size()];
        Header header = new Header.HeaderBuilder(rawValues.toArray(rawArray)).build();
        assertThat(header.getValues(), is(expectedValues));
    }

    @Test
    public void shouldCreateAppropriateValuesWhenValuesAreFollowedByBlanks() {
        final String [] headerValues = new String[]{"id", "dato_fom", "RANDOM", "dato_tom", "er_gyldig", "opprettet_av", "dato_opprettet", "endret_av", "dato_endret", "", ""};
        Header header = new Header.HeaderBuilder(headerValues).build();
        assertThat(header.getValues(), is(Arrays.asList(headerValues).subList(0, 9)));
    }
}