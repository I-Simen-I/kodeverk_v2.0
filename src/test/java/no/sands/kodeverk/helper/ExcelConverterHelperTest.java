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

        assertThat(convertedSheet.length, is(12));
        assertThat(convertedSheet[EXCEL_HEADER_ROW].length, is(11));
        assertTrue(convertedSheet[EXCEL_HEADER_ROW][0].equals("kode_k"));
        assertTrue(convertedSheet[EXCEL_HEADER_ROW][1].equals("decode"));
    }

    @Test
    public void testRemoveEmptyRowsInKodeverk() throws Exception {
        String[][] kodeverkWithEmptyRows = new String[4][2];
        kodeverkWithEmptyRows[0][0] = "kode1";
        kodeverkWithEmptyRows[0][1] = "kode2";
        kodeverkWithEmptyRows[1][0] = "kode3";
        kodeverkWithEmptyRows[1][1] = "kode4";
        kodeverkWithEmptyRows[2][0] = "kode5";
        kodeverkWithEmptyRows[2][1] = "";
        kodeverkWithEmptyRows[3][0] = "";
        kodeverkWithEmptyRows[3][1] = "";

        assertThat(kodeverkWithEmptyRows.length, is(4));

        String[][] validKodeverk = helper.removeEmptyRowsInKodeverk(kodeverkWithEmptyRows);
        assertThat(validKodeverk.length, is(3));
    }

    @Test
    public void testIsExcelSheetAValidKodeverk() throws Exception {
        assertFalse(ExcelConverterHelper.isExcelSheetAValidKodeverk("Main"));
        assertTrue(ExcelConverterHelper.isExcelSheetAValidKodeverk("AValidKodeverk"));
    }
}