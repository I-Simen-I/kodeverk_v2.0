package no.sands.kodeverk.converter.support;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.Header}
 *
 * @author Øyvind Strømmen
 */
public class HeaderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFailWhenHeaderIsEmpty() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_FIELDS);
        new Header().withRawValues(new String[]{});
    }

    @Test
    public void shouldFailWhenHeaderContainsNullValues() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_FIELDS);
        new Header().withRawValues(new String[]{"dato_endret", null});
    }

    @Test
    public void shouldFailWhenHeaderContainsDuplicates() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.DUPLICATE);

        List<String> rawValues = RequiredHeaderValue.getHeaderNames();
        rawValues.add(RequiredHeaderValue.DATO_ENDRET.getHeaderName());

        String[] rawArray = new String[rawValues.size()];
        new Header().withRawValues(rawValues.toArray(rawArray));
    }

    @Test
    public void shouldFailWhenOnlyRequiredFieldsArePresent() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.MISSING_FIELDS);

        List<String> rawValues = RequiredHeaderValue.getHeaderNames();

        String[] rawArray = new String[rawValues.size()];
        new Header().withRawValues(rawValues.toArray(rawArray));
    }

    @Test
    public void shouldCreateAppropriateValuesWhenRequiredFieldsArePresent() {
        List<String> rawValues = RequiredHeaderValue.getHeaderNames();
        rawValues.add("this field is an id");

        String[] rawArray = new String[rawValues.size()];

        Header header = new Header().withRawValues(rawValues.toArray(rawArray));
        assertThat(header.getValues(), is(rawValues));
    }

    @Test
    public void shouldFailWhenFieldsAreNotContinuous() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(CommonVariables.NON_CONTINUOUS);

        List<String> rawValues = RequiredHeaderValue.getHeaderNames();
        rawValues.add(null);
        rawValues.add("a_column");

        String[] rawArray = new String[rawValues.size()];
        new Header().withRawValues(rawValues.toArray(rawArray));
    }

    @Test
    public void shouldCreateAppropriateValuesWhenValidValuesAreFollowedByNullValues() {
        List<String> rawValues = RequiredHeaderValue.getHeaderNames();
        rawValues.add("this field is an id");

        List<String> expectedValues = new ArrayList<>(rawValues);

        rawValues.add(null);
        rawValues.add(null);

        String[] rawArray = new String[rawValues.size()];
        Header header = new Header().withRawValues(rawValues.toArray(rawArray));
        assertThat(header.getValues(), is(expectedValues));
    }
}