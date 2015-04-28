package no.sands.kodeverk.converter.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * This enum defines which header values all {@link no.sands.kodeverk.converter.support.Kodeverk} are required to contain.
 * Each type defines the formats that are accepted as input for that particular type.
 *
 * @author Øyvind Strømmen
 */
public enum HeaderType {

    DATO_FOM(ImmutableSet.of("dato_fom")),
    DATO_TOM(ImmutableSet.of("dato_tom")),
    ER_GYLDIG(ImmutableSet.of("er_gyldig")),
    OPPRETTET_AV(ImmutableSet.of("opprettet_av")),
    DATO_OPPRETTET(ImmutableSet.of("dato_opprettet")),
    ENDRET_AV(ImmutableSet.of("endret_av")),
    DATO_ENDRET(ImmutableSet.of("dato_endret"));

    private Set<String> validHeaderNames;

    private HeaderType(Set<String> validHeaderNames) {
        this.validHeaderNames = validHeaderNames;
    }

    public Set<String> getValidHeaderNames() {
        return validHeaderNames;
    }

    /**
     * Return a List of all possible header names, independent of which type they belong to.
     *
     * @return a List of possible header names
     */
    public static List<String> getHeaderNames() {
        List<String> headerNames = new ArrayList<>();
        for (HeaderType value : values()) {
            headerNames.addAll(value.validHeaderNames);
        }
        return headerNames;
    }

    /**
     * If possible, map a string value to a HeaderType
     *
     * @param rawValue the String which should be mapped
     * @return the mapped HeaderType if mapping was possible, null otherwise
     */
    public static HeaderType getType(String rawValue) {
        for (HeaderType headerType : values()) {
            if (headerType.validHeaderNames.contains(rawValue)) {
                return headerType;
            }
        }
        return null;
    }
}
