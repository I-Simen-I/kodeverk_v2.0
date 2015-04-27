package no.sands.kodeverk.converter.support;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Øyvind Strømmen
 */
public enum RequiredHeaderValue {

    DATO_FOM("dato_fom"),
    DATO_TOM("dato_tom"),
    ER_GYLDIG("er_gyldig"),
    OPPRETTET_AV("opprettet_av"),
    DATO_OPPRETTET("dato_opprettet"),
    ENDRET_AV("endret_av"),
    DATO_ENDRET("dato_endret");

    private String headerName;

    private RequiredHeaderValue(String name) {
        this.headerName = name;
    }

    public String getHeaderName() {
        return headerName;
    }

    public static List<String> getHeaderNames() {
        List<String> headerNames = new ArrayList<>();
        for (RequiredHeaderValue value : values()) {
            headerNames.add(value.getHeaderName());
        }
        return headerNames;
    }
}
