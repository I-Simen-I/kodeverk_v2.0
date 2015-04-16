package no.sands.kodeverk.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Simen Søhol
 */
public class SQLUtilTest {

    @Test
    public void testGetTimestampFormat() throws Exception {
        assertThat(SQLUtil.getTimestampFormat("2015-01-10 10:00"), is("TIMESTAMP('2015-01-10', '10:00')"));
    }

    @Test
    public void testGetDateFormat() throws Exception {
        assertThat(SQLUtil.getDateFormat("1991-01-01"), is("DATE('1991-01-01')"));
    }

    @Test
    public void testGetInsertStatementFormat() throws Exception {
        assertThat(SQLUtil.createInsertStatement("K_NAVN_1", "k_kode,dekode", "'kode_1','dette er en kode'"),
                is("INSERT INTO T_K_NAVN_1(k_kode,dekode) VALUES('kode_1','dette er en kode');\n"));
    }
}