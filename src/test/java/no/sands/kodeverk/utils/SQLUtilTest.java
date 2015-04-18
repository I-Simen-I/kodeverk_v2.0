package no.sands.kodeverk.utils;

import org.junit.Test;

import static no.sands.kodeverk.utils.SQLUtil.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Simen Søhol
 */
public class SQLUtilTest {

    @Test
    public void testGetTimestampFormat() throws Exception {
        assertThat(getTimestampFormat("2015-01-10 10:00"), is("TIMESTAMP('2015-01-10', '10:00')"));
    }

    @Test
    public void testGetDateFormat() throws Exception {
        assertThat(getDateFormat("1991-01-01"), is("DATE('1991-01-01')"));
    }

    @Test
    public void testGetInsertStatementFormat() throws Exception {
        assertThat(createInsertStatement("K_NAVN_1", "k_kode,dekode", "'kode_1','dette er en kode'"),
                is("INSERT INTO T_K_NAVN_1(k_kode,dekode) VALUES('kode_1','dette er en kode');\n"));
    }

    @Test
    public void testConvertColumnsToSQlValues() {
        int stringColumnIndex = 1;
        int dateColumnIndex = 2;
        int timestampColumnIndex = 3;
        int emptyColumnIndex = 4;
        int numberColumnIndex = 5;

        String columnTypeDate = "d1";
        String columnTypeTimestamp = "t1";
        String columnTypeString = "c1";
        String columnTypeOther = "i1";

        String[] values = {"kode", "dekode", "1900-01-01", "1900-01-01 10:00", "", "1"};

        assertThat(convertCSVValuesToSQlValues(columnTypeString, values[stringColumnIndex]), is("'dekode'"));
        assertThat(convertCSVValuesToSQlValues(columnTypeDate, values[dateColumnIndex]), is("DATE('1900-01-01')"));
        assertThat(convertCSVValuesToSQlValues(columnTypeTimestamp, values[timestampColumnIndex]),
                is("TIMESTAMP('1900-01-01', '10:00')"));
        assertThat(convertCSVValuesToSQlValues(columnTypeString, values[emptyColumnIndex]), is("null"));
        assertThat(convertCSVValuesToSQlValues(columnTypeOther, values[numberColumnIndex]), is("1"));
    }
}