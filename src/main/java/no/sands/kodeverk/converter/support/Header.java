package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Øyvind Strømmen
 */
public class Header {

    private List<String> values;

    private static final int MINIMUM_SIZE = RequiredHeaderValue.values().length + 1;

    public Header withRawValues(String [] rawValues) {
        List<String> requiredHeaderValues = RequiredHeaderValue.getHeaderNames();
        Set<String> uniqueValues = new HashSet<>();

        for (String rawValue : rawValues) {
            if (requiredHeaderValues.contains(rawValue)) {
                requiredHeaderValues.remove(rawValue);
            }

            if (!uniqueValues.add(rawValue)) {
                throw new KodeverkInvalidContentException("Header kan ikke inneholde duplikate verdier");
            }
        }

        if (requiredHeaderValues.isEmpty() && uniqueValues.size() >= MINIMUM_SIZE) {
            values = new ArrayList<>(uniqueValues);
            return this;
        }
        throw new KodeverkInvalidContentException("Header inneholdt ikke alle påkrevde felter eller manglet id-felt");
    }

    public List<String> getValues() {
        return values;
    }
}
