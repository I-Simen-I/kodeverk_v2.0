package no.sands.kodeverk.converter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import no.sands.kodeverk.generator.CSVGenerator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simen Søhol
 */
public class ExcelConverter {
    private CSVGenerator csvGenerator = new CSVGenerator();

    public void convertToCSV(String inputFile) throws Exception {
        File inputWorkbook = new File(inputFile);
        Workbook w = Workbook.getWorkbook(inputWorkbook);

        for (Sheet sheet : w.getSheets()) {
            if (isExcelSheetAValidKodeverk(sheet.getName())) {
                String[][] kodeverkList = storeSheetCellsInList(sheet);

                csvGenerator.generateCSVFiles(sheet.getName(), kodeverkList, sheet.getColumns());
            }
        }
    }

    /**
     * Find all cells in the excel sheet  and adds stores them in a 2D string array
     *
     * @param sheet the sheet to use
     * @return a 2D string array with all the cells in the excel sheet
     * @throws IOException
     */
    private String[][] storeSheetCellsInList(Sheet sheet) throws IOException {
        String[][] kodeverkList = new String[sheet.getRows()][sheet.getColumns()];

        for (int rowNumber = 0; rowNumber < sheet.getRows(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < sheet.getColumns(); columnNumber++) {
                Cell cell = sheet.getCell(columnNumber, rowNumber);
                kodeverkList[rowNumber][columnNumber] = cell.getContents();
            }
        }
        return kodeverkList;
    }

    /**
     * Checks if the excel sheet is a valid kodeverk
     *
     * @param sheetName the sheet name
     * @return true if the sheet is a vaild kodeverk
     */
    private static boolean isExcelSheetAValidKodeverk(String sheetName) {
        return !sheetName.equals("Main") && !sheetName.equals("Logg");
    }
}
