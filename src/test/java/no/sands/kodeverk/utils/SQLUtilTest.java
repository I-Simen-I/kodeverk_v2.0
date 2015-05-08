package no.sands.kodeverk.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import static no.sands.kodeverk.utils.SQLUtil.createInsertStatement;
import static no.sands.kodeverk.utils.SQLUtil.getDateFormat;
import static no.sands.kodeverk.utils.SQLUtil.getTimestampFormat;

import org.junit.Test;

/**
 * @author Simen Søhol
 */
public class SQLUtilTest {

    @Test
    public void testGetTimestampFormat() throws Exception {
        assertThat(getTimestampFormat("2015-01-10 10:00"), is("timestamp('2015-01-10','10:00')"));
    }

    @Test
    public void testGetDateFormat() throws Exception {
        assertThat(getDateFormat("1991-01-01"), is("date('1991-01-01')"));
    }

    @Test
    public void testGetInsertStatementFormat() throws Exception {
        assertThat(createInsertStatement("K_NAVN_1", "k_kode,dekode", "'kode_1','dette er en kode'"),
                is("insert into T_K_NAVN_1 (k_kode,dekode) VALUES ('kode_1','dette er en kode');\n"));
    }

    @Test
    public void testConvertColumnsToSQlValues() {
        //TODO: Fiks testen
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
/*
        new Column.ColumnBuilder("", 2, ).build();

        assertThat(convertCSVValuesToSQlValues(columnTypeString, values[stringColumnIndex]), is("'dekode'"));
        assertThat(convertCSVValuesToSQlValues(columnTypeDate, values[dateColumnIndex]), is("date('1900-01-01')"));
        assertThat(convertCSVValuesToSQlValues(columnTypeTimestamp, values[timestampColumnIndex]),
                is("timestamp('1900-01-01','10:00')"));
        assertThat(convertCSVValuesToSQlValues(columnTypeString, values[emptyColumnIndex]), is("NULL"));
        assertThat(convertCSVValuesToSQlValues(columnTypeOther, values[numberColumnIndex]), is("1"));*/
    }
}