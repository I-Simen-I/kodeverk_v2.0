package no.sands.kodeverk.helper;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static no.sands.kodeverk.common.CommonVariables.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * @author Simen Søhol
 */
public class CSVErrorHelperTest {
    private static final String TIMESTAMP = "1900-01-01 10:00";
    private static final String EMPTY_TIMESTAMP = "";
    private static final String DATE = "1900-01-01";
    private static final String EMPTY_DATE = "";
    private static final String[] HEADER_ROW = {"kode_k",
            COLUMN_DEKODE,
            COLUMN_GYLDIG,
            COLUMN_DATO_FOM,
            COLUMN_DATO_TOM,
            COLUMN_DATO_OPPRETTET,
            COLUMN_OPPRETTET_AV,
            COLUMN_DATO_ENDRET,
            COLUMN_ENDRET_AV
    };
    private static final String[] COLUMN_TYPE_ROW = {"c1", "c2", "c3", "d4", "d5", "t6", "c7", "t8", "c9"};

    private CSVErrorHelper helper = new CSVErrorHelper();

    @Test
    public void testRowContainOnlyEmpyValuesError() throws Exception {
        String[] row = {"", "", "", EMPTY_DATE, EMPTY_DATE, EMPTY_TIMESTAMP, "", EMPTY_TIMESTAMP, ""};

        List<String[]> csvList = Arrays.asList(HEADER_ROW, COLUMN_TYPE_ROW, row);

        List<String> errorList = helper.rowContainError("K_KDV", csvList, COLUMN_TYPE_ROW.length, FIRS_KODEVERK_ROW_WITH_VALUES);
        assertThat(errorList, hasSize(7));
    }

    @Test
    public void testRowContainWrongDateFormatError() throws Exception {
        String[] row = {"Kode1", "dekode", "0", DATE, "1900/01/01", TIMESTAMP, "Simen", TIMESTAMP, "Simen"};

        List<String[]> csvList = Arrays.asList(HEADER_ROW, COLUMN_TYPE_ROW, row);

        List<String> errorList = helper.rowContainError("K_KDV", csvList, COLUMN_TYPE_ROW.length, FIRS_KODEVERK_ROW_WITH_VALUES);
        assertThat(errorList, hasSize(1));
    }

    @Test
    public void testRowContainWrongTimestampFormatError() throws Exception {
        String[] row = {"Kode1", "dekode", "0", DATE, EMPTY_DATE, "1900/01/01 10:00", "Simen", TIMESTAMP, "Simen"};

        List<String[]> csvList = Arrays.asList(HEADER_ROW, COLUMN_TYPE_ROW, row);

        List<String> errorList = helper.rowContainError("K_KDV", csvList, COLUMN_TYPE_ROW.length, FIRS_KODEVERK_ROW_WITH_VALUES);
        assertThat(errorList, hasSize(1));
    }

    @Test
    public void testRowContainOnlyEmpyDateTomError() throws Exception {
        String[] row = {"Kode1", "dekode", "0", DATE, EMPTY_DATE, TIMESTAMP, "Simen", TIMESTAMP, "Simen"};

        List<String[]> csvList = Arrays.asList(HEADER_ROW, COLUMN_TYPE_ROW, row);

        List<String> errorList = helper.rowContainError("K_KDV", csvList, COLUMN_TYPE_ROW.length, FIRS_KODEVERK_ROW_WITH_VALUES);
        assertThat(errorList, hasSize(0));
    }
}