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

    private final List<String> values;

    private Header(HeaderBuilder builder) {
        this.values = builder.values;
    }

    public List<String> getValues() {
        return values;
    }

    public static class HeaderBuilder {

        private final String [] rawHeader;

        private List<String> values;
        private boolean hasFoundNullValue = false;
        private Set<String> uniqueValues = new LinkedHashSet<>();
        private List<String> requiredValues = HeaderType.getHeaderNames();

        public HeaderBuilder(String [] rawHeader) {
            this.rawHeader = rawHeader;
        }

        public Header build() {
            for (String rawValue : this.rawHeader) {
                validateContinuityOfValue(rawValue);
                validateUniquenessOfValue(rawValue);
                validateLengthOfValue(rawValue);
            }

            if (headerContainsRequiredValues()) {
                this.values = new ArrayList<>(this.uniqueValues);
                return new Header(this);
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
            requiredValues.remove(rawValue);

            if (StringUtils.isNotBlank(rawValue)) {
                if (!uniqueValues.add(rawValue)) {
                    throw new KodeverkInvalidContentException(CommonVariables.DUPLICATE);
                }
            }
        }

        /**
         * Header values cannot exceed 30 characters
         *
         * @param rawValue the value to validate
         */
        private void validateLengthOfValue(String rawValue) {
            if (StringUtils.length(rawValue) > 30) {
                throw new KodeverkInvalidContentException(CommonVariables.EXCEEDED_CHAR_LIMIT);
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
    }
}
