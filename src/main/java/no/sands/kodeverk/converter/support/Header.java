package no.sands.kodeverk.converter.support;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Representation of the header row of a {@link no.sands.kodeverk.converter.support.Kodeverk}. The header is the top most row of a kodeverk,
 * and must contain at minimun a set of required values defined by {@link HeaderType}.
 *
 * @author Øyvind Strømmen
 */
public class Header {

    private List<String> values;

    private Kodeverk kodeverk;

    private boolean hasFoundNullValue = false;

    private Set<String> uniqueValues = new LinkedHashSet<>();

    private List<String> requiredValues = HeaderType.getHeaderNames();

    /**
     * Builder method for supplying this header with raw values. The values provided will be validated against a set of required fields,
     * for continuity, uniqueness etc.
     *
     * @param rawValues an array representation of the header row
     * @return this header if all provided values validated ok
     */
    public Header withRawValues(String [] rawValues) {
        for (String rawValue : rawValues) {
            validateContinuityOfValue(rawValue);
            validateUniquenessOfValue(rawValue);
        }

        if (headerContainsRequiredValues()) {
            values = new ArrayList<>(uniqueValues);
            return this;
        }
        throw new KodeverkInvalidContentException(CommonVariables.MISSING_FIELDS);
    }

    /**
     * Null values cannot be followed by non null values
     *
     * @param rawValue the value to validate
     */
    private void validateContinuityOfValue(String rawValue) {
        if (StringUtils.isBlank(rawValue)) {
            hasFoundNullValue = true;
        } else if (hasFoundNullValue) {
            throw new KodeverkInvalidContentException(CommonVariables.NON_CONTINUOUS);
        }
    }

    /**
     * A set of header values cannot contain duplicates
     *
     * @param rawValue the value to validate
     */
    private void validateUniquenessOfValue(String rawValue) {
        if (requiredValues.contains(rawValue)) {
            requiredValues.remove(rawValue);
        }

        if (StringUtils.isNotBlank(rawValue)) {
            if (!uniqueValues.add(rawValue)) {
                throw new KodeverkInvalidContentException(CommonVariables.DUPLICATE);
            }
        }
    }

    /**
     * Determine if the header contains all required values
     *
     * @return true if the header contains all required values, false otherwise
     */
    private boolean headerContainsRequiredValues() {
        final int minimun_size = HeaderType.values().length + 1;
        return (requiredValues.isEmpty()) && (uniqueValues.size() >= minimun_size);
    }

    /**
     * Builder method for supplying a backward reference to the {@link no.sands.kodeverk.converter.support.Kodeverk} this
     * Header belongs to
     *
     * @param kodeverk the kodeverk
     * @return a referance to this Header
     */
    public Header withKodeverk(Kodeverk kodeverk) {
        this.kodeverk = kodeverk;
        return this;
    }

    public List<String> getValues() {
        return values;
    }

    public Kodeverk getKodeverk() {
        return kodeverk;
    }
}
