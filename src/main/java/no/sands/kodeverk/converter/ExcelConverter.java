package no.sands.kodeverk.converter;

import jxl.Sheet;
import jxl.Workbook;
import no.sands.kodeverk.generator.CSVGenerator;
import no.sands.kodeverk.helper.ExcelConverterHelper;

import java.io.File;

import static no.sands.kodeverk.helper.ExcelConverterHelper.isExcelSheetAValidKodeverk;

/**
 * @author Simen Søhol
 */
public class ExcelConverter {
    private ExcelConverterHelper excelConverterHelper = new ExcelConverterHelper();
    private CSVGenerator csvGenerator = new CSVGenerator();

    public void convertToCSV(String inputFile) throws Exception {
        File inputWorkbook = new File(inputFile);
        Workbook w = Workbook.getWorkbook(inputWorkbook);

        for (Sheet sheet : w.getSheets()) {
            if (isExcelSheetAValidKodeverk(sheet.getName())) {
                String[][] kodeverkList = excelConverterHelper.mapSheetToArray(sheet);

                csvGenerator.generateCSVFiles(sheet.getName(), kodeverkList);
            }
        }
    }


}
