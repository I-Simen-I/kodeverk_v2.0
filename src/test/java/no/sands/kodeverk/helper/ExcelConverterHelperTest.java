package no.sands.kodeverk.helper;

import jxl.Workbook;
import org.junit.Test;

import java.io.File;

import static no.sands.kodeverk.common.CommonVariables.EXCEL_HEADER_ROW;
import static no.sands.kodeverk.common.CommonVariables.TEST_FILE_1;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Simen Søhol
 */
public class ExcelConverterHelperTest {
    private ExcelConverterHelper helper = new ExcelConverterHelper();

    @Test
    public void testMapSheetToArray() throws Exception {
        File inputWorkbook = new File(TEST_FILE_1);
        Workbook w = Workbook.getWorkbook(inputWorkbook);

        String[][] convertedSheet = helper.mapSheetToArray(w.getSheet(0));

        assertThat(convertedSheet.length, is(5));
        assertThat(convertedSheet[EXCEL_HEADER_ROW].length, is(10));
        assertTrue(convertedSheet[EXCEL_HEADER_ROW][0].equals("kode_k"));
        assertTrue(convertedSheet[EXCEL_HEADER_ROW][1].equals("decode"));
    }

    @Test
    public void testIsExcelSheetAValidKodeverk() throws Exception {
        assertFalse(ExcelConverterHelper.isExcelSheetAValidKodeverk("Main"));
        assertTrue(ExcelConverterHelper.isExcelSheetAValidKodeverk("AValidKodeverk"));
    }
}