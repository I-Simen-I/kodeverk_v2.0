package no.sands.kodeverk.domain;

import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableSet;

/**
 * This enum defines which data types are accepted in the {@link DataTypes} row.
 * Each type defines the formats that are accepted as input for that particular type.
 *
 * @author Øyvind Strømmen
 */
public enum DataType {

    CHARACTERS(ImmutableSet.of("c\\d*")),
    TIMESTAMP(ImmutableSet.of("t\\d*")),
    DATE(ImmutableSet.of("d\\d*")),
    INDEX(ImmutableSet.of("i\\d*"));

    private Set<String> validDataTypeNames;

    private DataType(Set<String> validDataTypeNames) {
        this.validDataTypeNames = validDataTypeNames;
    }

    /**
     * If possible, map a string value to a DataType
     *
     * @param rawValue the String which should be mapped
     * @return the mapped DataType if mapping was possible, null otherwise
     */
    public static DataType getType(String rawValue) {
        if (rawValue != null) {
            for (DataType dataType : values()) {
                for (String validPattern : dataType.validDataTypeNames) {
                    if (Pattern.matches(validPattern, rawValue)) {
                        return dataType;
                    }
                }
            }
        }
        return null;
    }
}
