package no.sands.kodeverk.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Map;

import org.junit.Test;

import no.sands.kodeverk.common.CommonVariables;

/**
 * @author Simen Søhol
 */
public class CSVToSQLConverterTest {

    @Test
    public void testGenerateSQL() throws Exception {
        CSVToSQLConverter converter = new CSVToSQLConverter();

        Map<String, Integer> insertStats = converter.generateSQL(CommonVariables.SQL_FILE_PATH + "inserts.sql");
        assertThat(insertStats.get("K_NAVN_1"), is(6));
        assertThat(insertStats.get("K_NAVN_2"), is(6));
        assertThat(insertStats.get("K_NAVN_3"), is(9));
    }
}