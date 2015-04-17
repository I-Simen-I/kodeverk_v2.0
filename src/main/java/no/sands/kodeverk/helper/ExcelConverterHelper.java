package no.sands.kodeverk.helper;

import jxl.Cell;
import jxl.Sheet;

import java.io.IOException;

import static java.lang.System.arraycopy;
import static no.sands.kodeverk.common.CommonVariables.DATE_COLUMN;
import static no.sands.kodeverk.common.CommonVariables.TIMESTAMP_COLUMN;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author Simen Søhol
 */
public class ExcelConverterHelper {

    /**
     * Checks if the excel sheet is a valid kodeverk
     *
     * @param sheetName the sheet name
     * @return true if the sheet is a vaild kodeverk
     */
    public static boolean isExcelSheetAValidKodeverk(String sheetName) {
        return !sheetName.equals("Main")
                && !sheetName.equals("Logg");
    }

    /**
     * Find all cells in the excel sheet  and adds stores them in a 2D string array
     *
     * @param sheet the sheet to use
     * @return a 2D string array[row][column] with all the cells in the excel sheet
     * @throws IOException
     */
    public String[][] mapSheetToArray(Sheet sheet) throws IOException {
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
     * Removes all the empty lines in the kodeverk file. If the first column in a row has a value,
     * then it is a valid row.
     *
     * @param kodeverkList the kodeverklist to filter
     * @return a list with with only valid rows
     */
    public String[][] removeEmptyRowsInKodeverk(String[][] kodeverkList) {
        int fileLengthWithoutEmptyLines = kodeverkList.length - emptyRowCounter(kodeverkList);
        String[][] kodeverkListWithoutEmptyLines = new String[fileLengthWithoutEmptyLines][kodeverkList[0].length];

        arraycopy(kodeverkList, 0, kodeverkListWithoutEmptyLines, 0, kodeverkListWithoutEmptyLines.length);

        return kodeverkListWithoutEmptyLines;
    }

    /**
     * Checks if column is a date column
     *
     * @param columnType the columnType row to check
     * @param column     the column to check
     * @return true if column is a date
     */
    public boolean isColumnADate(String[] columnType, int column) {
        return isColumnOfType(columnType, column, DATE_COLUMN);
    }

    /**
     * Checks if column is a timestamp column
     *
     * @param columnType the columnType row to check
     * @param column     the column to check
     * @return true if column is a timestamp
     */
    public boolean isColumnATimestamp(String[] columnType, int column) {
        return isColumnOfType(columnType, column, TIMESTAMP_COLUMN);
    }

    private int emptyRowCounter(String[][] kodeverkList) {
        int emptyRowsCounter = 0;

        for (String[] kodeverkRow : kodeverkList) {
            if (isBlank(kodeverkRow[0])) {
                emptyRowsCounter++;
            }
        }
        return emptyRowsCounter;
    }

    private boolean isColumnOfType(String[] columnType, int column, char type) {
        return !isEmpty(columnType[column])
                && columnType[column].charAt(0) == type;
    }
}