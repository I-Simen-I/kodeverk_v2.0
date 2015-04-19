package no.sands.kodeverk.common;

/**
 * @author Simen S�hol
 */
public class CommonVariables {
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
    public static final String COLUMN_DECODE = "decode";
    public static final String COLUMN_DEKODE = "dekode";
    public static final String COLUMN_DATO_FOM = "dato_fom";
    public static final String COLUMN_DATO_TOM = "dato_tom";
    public static final String COLUMN_GYLDIG = "gyldig";
    public static final String COLUMN_ENDRET_AV = "endret_av";
    public static final String COLUMN_OPPRETTET_AV = "opprettet_av";
    public static final String COLUMN_DATO_OPPRETTET = "dato_opprettet";
    public static final String COLUMN_DATO_ENDRET = "dato_endret";
    public static final char TEXT_COLUMN = 'c';
    public static final char TIMESTAMP_COLUMN = 't';
    public static final char DATE_COLUMN = 'd';
    public static final int FIRS_KODEVERK_ROW_WITH_VALUES = 2;
    public static final int EXCEL_HEADER_ROW = 1;
    public static final int EXCEL_COLUMN_TYPE_ROW = 0;
    public static final int CSV_HEADER_ROW = 0;
    public static final int CSV_COLUMN_TYPE_ROW = 1;
    public static final int FIRST_COLUMN = 0;

    //SQL
    public static final String SQL_NULL_VALUE = "null";
    public static final String SQL_EMPTY_VALUE = "";

    //Error messages
    public static final String SQL_FIRST_COLUMN_IS_EMPTY_ERROR_MESSAGE = "Feil i kodeverk: %s. Kodeverket har en ingen verdi p� rad %s";
    public static final String SQL_NO_VALUE_ERROR_MESSAGE = "Feil i kodeverk: %s. Feltet %s m� v�re satt for verdien %s";
    public static final String SQL_WRONG_DATE_FORMAT_ERROR_MESSAGE = "Feil i kodeverk: %s. Kodeverksverdien %s inneholder en dato med feil formate";
    public static final String SQL_WRONG_TIMESTAMP_FORMAT_ERROR_MESSAGE = "Feil i kodeverk: %s. Kodeverksverdien %s inneholder et timestamp med feil formate";
}
