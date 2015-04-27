package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Øyvind Strømmen
 */
public class HeaderTest {

    private static final String DUPLICATE = "Header kan ikke inneholde duplikate verdier";

    private static final String MISSING_FIELDS = "Header inneholdt ikke alle påkrevde felter";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFailWhenHeaderIsEmpty() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(MISSING_FIELDS);
        new Header().withRawValues(new String[]{});
    }

    @Test
    public void shouldFailWhenHeaderContainsNullValues() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(MISSING_FIELDS);
        new Header().withRawValues(new String[]{"dato_endret", null});
    }

    @Test
    public void shouldFailWhenHeaderContainsDuplicates() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(DUPLICATE);

        List<String> rawValues = RequiredHeaderValue.getHeaderNames();
        rawValues.add(RequiredHeaderValue.DATO_ENDRET.getHeaderName());

        String[] rawArray = new String[rawValues.size()];
        new Header().withRawValues(rawValues.toArray(rawArray));
    }

    @Test
    public void shouldFailWhenOnlyRequiredFieldsArePresent() {
        expectedException.expect(KodeverkInvalidContentException.class);
        expectedException.expectMessage(MISSING_FIELDS);

        List<String> rawValues = RequiredHeaderValue.getHeaderNames();

        String[] rawArray = new String[rawValues.size()];
        new Header().withRawValues(rawValues.toArray(rawArray));
    }

    @Test
    public void shouldCreateAproppiateValuesWhenOnlyRequiredFieldsArePresent() {
        List<String> rawValues = RequiredHeaderValue.getHeaderNames();
        rawValues.add("this field is an id");

        String[] rawArray = new String[rawValues.size()];

        Header header = new Header().withRawValues(rawValues.toArray(rawArray));
        assertThat(header.getValues().containsAll(rawValues), is(Boolean.TRUE));
    }
}