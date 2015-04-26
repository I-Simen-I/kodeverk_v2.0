package no.sands.kodeverk.common;

/**
 * @author Simen Søhol
 */
public class CommonVariables {
    //Paths
    public static final String TEST_FILE_PATH = "../kodeverk_v2.0/src/test/resources/testdata/";
    public static final String KODEVERK_FILE_PATH = "../kodeverk_v2.0/src/main/resources/kodeverk/";
    public static final String SQL_FILE_PATH = "../kodeverk_v2.0/src/main/resources/sqlFiles/";
    public static final String KODEVERK_FILE = "W:/Simen/Kode/Kodeverk_2.0/PEN.xls";

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
}
