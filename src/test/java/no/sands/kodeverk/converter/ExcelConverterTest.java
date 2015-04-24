package no.sands.kodeverk.converter;

import static no.sands.kodeverk.common.CommonVariables.TEST_FILE_1;

import org.junit.Test;

/**
 * @author Simen Søhol
 */
public class ExcelConverterTest {
    private ExcelConverter converter = new ExcelConverter();

    @Test
    public void convertExcelfileToCSVfiles() throws Exception {
        converter.convertToCSV(TEST_FILE_1);
    }
}