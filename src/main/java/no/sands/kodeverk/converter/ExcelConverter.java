package no.sands.kodeverk.converter;

import jxl.Sheet;
import jxl.Workbook;
import no.sands.kodeverk.generator.CSVGenerator;
import no.sands.kodeverk.helper.ExcelConverterHelper;

import java.io.File;
import java.util.List;

import static no.sands.kodeverk.helper.ExcelConverterHelper.isExcelSheetAValidKodeverk;

/**
 * @author Simen Søhol
 */
public class ExcelConverter {
    private ExcelConverterHelper excelConverterHelper = new ExcelConverterHelper();
    private CSVGenerator csvGenerator = new CSVGenerator();

    public void convertToCSV(String inputFile) throws Exception {
        File inputWorkbook = new File(inputFile);
        Workbook workbook = Workbook.getWorkbook(inputWorkbook);

        for (Sheet sheet : workbook.getSheets()) {
            if (isExcelSheetAValidKodeverk(sheet.getName())) {
                List<String[]> mappedList = excelConverterHelper.mapSheet(sheet);
                List<String[]> kodeverkList = excelConverterHelper.removeEmptyRowsInKodeverk(mappedList);

                csvGenerator.generateCSVFiles(sheet.getName(), kodeverkList);
            }
        }
    }


}
