package no.sands.kodeverk.kodeverkinport;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import no.sands.kodeverk.utils.SQLGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static no.sands.kodeverk.common.CommonVariables.NIG;
import static no.sands.kodeverk.common.CommonVariables.NTG;

/**
 * @author Simen Søhol
 */
public class ExcelReader {
    private Map<String, Integer> statusList = new HashMap<String, Integer>();

    public Map<String, Integer> read(String inputFile) throws Exception {
        File inputWorkbook = new File(inputFile);
        int numberOfKodeverksTablesGenerated = 0;
        int numberOfTotalInserts = 0;
        Workbook w = Workbook.getWorkbook(inputWorkbook);

        for (Sheet sheet : w.getSheets()) {
            String[][] kodeverkList = new String[sheet.getRows()][sheet.getColumns()];
            SQLGenerator sqlGenerator = new SQLGenerator();

            for (int rowNumber = 0; rowNumber < sheet.getRows(); rowNumber++) {
                for (int columnNumber = 0; columnNumber < sheet.getColumns(); columnNumber++) {
                    Cell cell = sheet.getCell(columnNumber, rowNumber);
                    kodeverkList[rowNumber][columnNumber] = cell.getContents();
                }
            }

            List<String> generatedSQLInserts = sqlGenerator.generateSQL(sheet.getName(), kodeverkList, sheet.getColumns());

            statusList.put(sheet.getName(), generatedSQLInserts.size());
            numberOfKodeverksTablesGenerated++;
            numberOfTotalInserts += generatedSQLInserts.size();
        }

        statusList.put(NTG, numberOfKodeverksTablesGenerated);
        statusList.put(NIG, numberOfTotalInserts);

        return statusList;
    }
}
