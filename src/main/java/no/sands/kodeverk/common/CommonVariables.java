package no.sands.kodeverk.common;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import no.sands.kodeverk.converter.support.DataType;
import no.sands.kodeverk.converter.support.HeaderType;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class CommonVariables {
    //Encoding
    public static final String ENCODING_WINDOWS_1252 = "Windows-1252";

    //Paths
    public static final String TEST_FILE_PATH = "../kodeverk_v2.0/src/test/resources/testdata/";
    public static final String KODEVERK_FILE_PATH = "../kodeverk_v2.0/src/main/resources/kodeverk/";
    public static final String SQL_FILE_PATH = "../kodeverk_v2.0/src/main/resources/sqlFiles/";

    //Files
    public static final String TEST_FILE_1 = TEST_FILE_PATH + "kodeverk_to_import.xls";

    //File type endings
    public static final String CSV_FILE = ".csv";
    public static final String XLS_FILE = ".xls";

    //Column types
    public static final String COLUMN_DEKODE = "dekode";
    public static final String COLUMN_DATO_FOM = "dato_fom";
    public static final String COLUMN_DATO_TOM = "dato_tom";
    public static final String COLUMN_GYLDIG = "er_gyldig";
    public static final String COLUMN_ENDRET_AV = "endret_av";
    public static final String COLUMN_OPPRETTET_AV = "opprettet_av";
    public static final String COLUMN_DATO_OPPRETTET = "dato_opprettet";
    public static final String COLUMN_DATO_ENDRET = "dato_endret";
    public static final char TEXT_COLUMN = 'c';
    public static final char TIMESTAMP_COLUMN = 't';
    public static final char DATE_COLUMN = 'd';
    public static final int FIRST_KODEVERK_ROW_WITH_VALUES = 2;
    public static final int EXCEL_HEADER_ROW = 1;
    public static final int EXCEL_COLUMN_TYPE_ROW = 0;
    public static final int CSV_HEADER_ROW = 0;
    public static final int CSV_COLUMN_TYPE_ROW = 1;
    public static final int FIRST_COLUMN = 0;

    //SQL
    public static final String SQL_NULL_VALUE = "NULL";
    public static final String SQL_EMPTY_VALUE = "";

    //CSV
    public static final String CSV_NULL_VALUE = "NULL";
    public static final String CSV_VALID_GYLDIG_VALUE_0 = "0";
    public static final String CSV_VALID_GYLDIG_VALUE_1 = "1";

    //Error messages
    public static final String SQL_FIRST_COLUMN_IS_EMPTY_ERROR_MESSAGE = "Feil i kodeverk: %s. Kodeverket har en ingen verdi på rad %s";
    public static final String SQL_NO_VALUE_ERROR_MESSAGE = "Feil i kodeverk: %s. Feltet %s må være satt for koden %s";
    public static final String SQL_WRONG_DATE_FORMAT_ERROR_MESSAGE = "Feil i kodeverk: %s. Feltet %s må ha riktig format(yyyy-MM-dd) for koden %s";
    public static final String SQL_WRONG_TIMESTAMP_FORMAT_ERROR_MESSAGE = "Feil i kodeverk: %s. Feltet %s må ha riktig format(yyyy-MM-dd hh:mm) for koden %s";
    public static final String SQL_WRONG_VALUE_GYLDIG = "Feil i kodeverk: %s. Feltet %s må ha verdien 0 eller 1 for koden %s";

    public static final String DUPLICATE = "Header raden kan ikke inneholde duplikate verdier";
    public static final String MISSING_FIELDS = "Header raden inneholdt ikke alle påkrevde felter";
    public static final String NON_CONTINUOUS = "Header kolonner uten innhold kan ikke etterfølges av kolonner med innhold";
    public static final String DATATYPE_DOESNT_MATCH_HEADER = "Datatype matcher ikke header";
    public static final String INVALID_DATA_TYPE = "Ugyldig datatype";
    public static final String EXCEEDED_CHAR_LIMIT = "Headerfelt kan ikke være lengre enn 30 tegn";
    public static final String INVALID_FIRST_COLUMN = "Første kolonne skal enten være datatype 'i' eller 'c'";

    //Config
    public static final Map<HeaderType, DataType> headerDataTypeMap = ImmutableMap.<HeaderType, DataType>builder()
            .put(HeaderType.DATO_FOM, DataType.DATE)
            .put(HeaderType.DATO_TOM, DataType.DATE)
            .put(HeaderType.ER_GYLDIG, DataType.CHARACTERS)
            .put(HeaderType.DATO_OPPRETTET, DataType.TIMESTAMP)
            .put(HeaderType.OPPRETTET_AV, DataType.CHARACTERS)
            .put(HeaderType.DATO_ENDRET, DataType.TIMESTAMP)
            .put(HeaderType.ENDRET_AV, DataType.CHARACTERS)
            .build();
}
