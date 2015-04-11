package no.sands.kodeverk.excelconverter;

import org.junit.Test;

import java.util.Map;

import static no.sands.kodeverk.common.CommonVariables.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Simen Søhol
 */
public class ExcelReaderTest {

    @Test
    public void generateSQLInserts() throws Exception {
        ExcelReader generatedInserts = new ExcelReader();
        Map<String, Integer> status = generatedInserts.read(TEST_FILE_1);

        assertThat(status.get(NTG), is(3));
        assertThat(status.get(NIG), is(585));
    }
}