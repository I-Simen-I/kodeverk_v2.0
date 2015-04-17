package no.sands.kodeverk.helper;

import jxl.Workbook;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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

        List<String[]> convertedSheet = helper.mapSheet(w.getSheet(0));

        assertThat(convertedSheet.size(), is(12));
        assertThat(convertedSheet.get(EXCEL_HEADER_ROW).length, is(12));
        assertTrue(convertedSheet.get(EXCEL_HEADER_ROW)[0].equals("kode_k"));
        assertTrue(convertedSheet.get(EXCEL_HEADER_ROW)[1].equals("decode"));
    }

    @Test
    public void testRemoveEmptyRowsInKodeverk() throws Exception {
        String[] row1 = {"kode1", "kode2"};
        String[] row2 = {"kode3", "kode4"};
        String[] row3 = {"kode5", ""};
        String[] row4 = {"", " "};
        String[] row5 = {"   ", "        "};

        List<String[]> kodeverkWithEmptyRows = Arrays.asList(row1, row2, row3, row4, row5);

        assertThat(kodeverkWithEmptyRows.size(), is(5));

        List<String[]> validKodeverk = helper.removeEmptyRowsInKodeverk(kodeverkWithEmptyRows);
        assertThat(validKodeverk.size(), is(3));
    }

    @Test
    public void testIsExcelSheetAValidKodeverk() throws Exception {
        assertFalse(ExcelConverterHelper.isExcelSheetAValidKodeverk("Main"));
        assertTrue(ExcelConverterHelper.isExcelSheetAValidKodeverk("AValidKodeverk"));
    }
}