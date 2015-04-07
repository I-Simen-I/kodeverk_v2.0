package no.sands.kodeverk.excelconverter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import no.sands.kodeverk.utils.CSVGenerator;

import java.io.File;

/**
 * @author Simen Søhol
 */
public class ExcelConverter {
    private CSVGenerator csvGenerator = new CSVGenerator();

    public void convertToCSV(String inputFile) throws Exception {
        File inputWorkbook = new File(inputFile);
        Workbook w = Workbook.getWorkbook(inputWorkbook);

        for (Sheet sheet : w.getSheets()) {
            String[][] kodeverkList = new String[sheet.getRows()][sheet.getColumns()];

            for (int rowNumber = 0; rowNumber < sheet.getRows(); rowNumber++) {
                for (int columnNumber = 0; columnNumber < sheet.getColumns(); columnNumber++) {
                    Cell cell = sheet.getCell(columnNumber, rowNumber);
                    kodeverkList[rowNumber][columnNumber] = cell.getContents();
                }
            }

            csvGenerator.generateCSVFiles(sheet.getName(), kodeverkList, sheet.getColumns());
        }
    }
}
