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

    private List<String> values = new ArrayList<>();

    private Kodeverk kodeverk;

    private boolean hasFoundNullValue = false;

    /**
     * Builder method for supplying this data types field with raw values. The values provided will go through several
     * layers of validation, including correnspondence against the header etc.
     *
     * @param rawValues an array representation of the data types row
     * @return this datatypes if all provided values where sucsesfully validated
     */
    public DataTypes withRawValues(String[] rawValues) {

        int columnNumber = 0;

        for (String rawValue : rawValues) {
            validateType(rawValue);
            validateContinuityOfValue(rawValue);
            validateIndex(rawValue, columnNumber);
            validateAgainstCorrespondingHeader(rawValue, columnNumber);

            columnNumber++;

            if (StringUtils.isNotBlank(rawValue)) {
                this.values.add(rawValue);
            }
        }
        return this;
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
     * Index fields must be of type {@link no.sands.kodeverk.converter.support.DataType#INDEX}
     *
     * @param rawValue the value to validate
     * @param columnNumber the columnNumber of the value to validate
     */
    private void validateIndex(String rawValue, int columnNumber) {
        if (columnNumber == 0) {
            DataType dataType = DataType.getType(rawValue);
            HeaderType headerType = HeaderType.getType(kodeverk.getHeader().getValues().get(columnNumber));

            if (headerType == null && dataType != DataType.INDEX) {
                throw new KodeverkInvalidContentException(CommonVariables.MISSING_INDEX);
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
        List<String> headerValues = kodeverk.getHeader().getValues();

        if (columnNumber < headerValues.size()) {
            HeaderType headerType = HeaderType.getType(headerValues.get(columnNumber));

            if (headerType != null && !EnumSet.of(Kodeverk.headerDataTypeMap.get(headerType)).contains(dataType)) {
                throw new KodeverkInvalidContentException(CommonVariables.DATATYPE_DOESNT_MATCH_HEADER);
            }
        }
    }

    /**
     * Builder method for supplying a backward reference to the {@link no.sands.kodeverk.converter.support.Kodeverk} this
     * DataTypes belongs to
     *
     * @param kodeverk the kodeverk
     * @return a referance to this DataTypes
     */
    public DataTypes withKodeverk(Kodeverk kodeverk) {
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
