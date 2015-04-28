package no.sands.kodeverk.converter.support;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * This enum defines which data types are accepted in the {@link no.sands.kodeverk.converter.support.DataTypes} row.
 * Each type defines the formats that are accepted as input for that particular type.
 *
 * @author Øyvind Strømmen
 */
public enum DataType {

    CHARACTERS(ImmutableSet.of("c")),
    TIMESTAMP(ImmutableSet.of("t")),
    DATE(ImmutableSet.of("d")),
    INDEX(ImmutableSet.of("i"));

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
        for (DataType dataType : values()) {
            if (dataType.validDataTypeNames.contains(rawValue)) {
                return dataType;
            }
        }
        return null;
    }
}
