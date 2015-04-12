package no.sands.kodeverk.converter;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Simen Søhol
 */
public class CSVToSQLConverterTest {

    @Test
    public void testGenerateSQL() throws Exception {
        CSVToSQLConverter converter = new CSVToSQLConverter();

        Map<String, Integer> insertStats = converter.generateSQL();
        assertThat(insertStats.get("K_NAVN_1"), is(3));
        assertThat(insertStats.get("K_NAVN_2"), is(6));
        assertThat(insertStats.get("K_NAVN_3"), is(574));
    }
}