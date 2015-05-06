package no.sands.kodeverk.converter.support;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Representation of the data type row of a {@link no.sands.kodeverk.converter.support.Kodeverk}. The data types row lies second from the
 * top in a kodeverk. Accepted values are defined in {@link no.sands.kodeverk.converter.support.DataType}
 *
 * @author Øyvind Strømmen
 */
public class DataTypes {

    private final List<String> values;

    private final Header header;

    private DataTypes(DataTypesBuilder builder) {
        this.values = builder.values;
        this.header = builder.header;
    }

    public List<String> getValues() {
        return values;
    }

    public Header getHeader() {
        return header;
    }

    public static class DataTypesBuilder {

        private final String[] rawDataTypes;
        private final Header header;

        private List<String> values = new ArrayList<>();
        private boolean hasFoundNullValue = false;

        public DataTypesBuilder(String[] rawDataTypes, Header header) {
            this.rawDataTypes = rawDataTypes;
            this.header = header;
        }

        public DataTypes build() {
            for (int columnNumber = 0; columnNumber < this.rawDataTypes.length; columnNumber++) {

                String rawValue = this.rawDataTypes[columnNumber];

                validateType(rawValue);
                validateContinuityOfValue(rawValue);
                validateFirstColumn(rawValue, columnNumber);
                validateAgainstCorrespondingHeader(rawValue, columnNumber);

                if (StringUtils.isNotBlank(rawValue)) {
                    this.values.add(rawValue);
                }
            }
            return new DataTypes(this);
        }

        /**
         * Values must be of types defined by {@link no.sands.kodeverk.converter.support.DataType} only
         *
         * @param rawValue the value to validate
         */
        private void validateType(String rawValue) {
            if (StringUtils.isNotBlank(rawValue) && DataType.getType(rawValue) == null) {
                throw new KodeverkInvalidContentException(CommonVariables.INVALID_DATA_TYPE);
            }
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
         *
         *
         * @param rawValue the value to validate
         * @param columnNumber the columnNumber of the value to validate
         */
        private void validateFirstColumn(String rawValue, int columnNumber) {
            if (columnNumber == 0) {
                DataType dataType = DataType.getType(rawValue);
                HeaderType headerType = HeaderType.getType(this.header.getValues().get(columnNumber));

                if (headerType == null && (StringUtils.isBlank(rawValue) || !EnumSet.of(DataType.INDEX, DataType.CHARACTERS).contains(dataType))) {
                    throw new KodeverkInvalidContentException(CommonVariables.INVALID_FIRST_COLUMN);
                }
            }
        }

        /**
         * A data type must match the header value within the same column
         *
         * @param rawValue the value to validate
         * @param columnNumber the columnNumber of the value to validate
         */
        private void validateAgainstCorrespondingHeader(String rawValue, int columnNumber) {
            DataType dataType = DataType.getType(rawValue);
            List<String> headerValues = this.header.getValues();

            if (columnNumber < headerValues.size() && StringUtils.isNotBlank(rawValue)) {
                HeaderType headerType = HeaderType.getType(headerValues.get(columnNumber));

                if (headerType != null && !EnumSet.of(Kodeverk.headerDataTypeMap.get(headerType)).contains(dataType)) {
                    throw new KodeverkInvalidContentException(CommonVariables.DATATYPE_DOESNT_MATCH_HEADER);
                }
            }
        }
    }
}
