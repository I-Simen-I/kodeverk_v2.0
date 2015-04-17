package no.sands.kodeverk.helper;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import static no.sands.kodeverk.common.CommonVariables.COLUMN_DECODE;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_DEKODE;
import static no.sands.kodeverk.common.CommonVariables.DATE_COLUMN;
import static no.sands.kodeverk.common.CommonVariables.EXCEL_COLUMN_TYPE_ROW;
import static no.sands.kodeverk.common.CommonVariables.EXCEL_HEADER_ROW;
import static no.sands.kodeverk.common.CommonVariables.TEXT_COLUMN;
import static no.sands.kodeverk.common.CommonVariables.TIMESTAMP_COLUMN;
import static no.sands.kodeverk.utils.DateUtil.convertDateString;
import static no.sands.kodeverk.utils.DateUtil.convertTimestampString;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jxl.Cell;
import jxl.Sheet;

/**
 * @author Simen S�hol
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
     * Adds a blank string if column is the last in a row, otherwise a comma
     *
     * @param column  the current column
     * @param columns number of columns in row
     * @return blank string if column is the last in a row, otherwise a comma
     */
    public static String addCommmaSeparator(int column, int columns) {
        if (column != columns - 1) {
            return ",";
        }
        return "";
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
     * Returns the header row in the given kodeverkList
     *
     * @param kodeverklList the kodeverklist to get the header from
     * @return the header in the list
     */
    public String getHeaderRow(String[][] kodeverklList) {
        return getColumnAtRow(EXCEL_HEADER_ROW, kodeverklList);
    }

    /**
     * Returns the column type row in the given kodeverkList
     *
     * @param kodeverklList the kodeverklist to get the column type row from
     * @return the comumn type row in the list
     */
    public String getColumnTypeRow(String[][] kodeverklList) {
        return getColumnAtRow(EXCEL_COLUMN_TYPE_ROW, kodeverklList);
    }

    public String getValuesAtRow(String[] header, String[] columnType, String[] values, int row) {
        StringBuilder columnBuilder = new StringBuilder();
        if (!isRowColumnTypeOrHeader(row)) {
            int numberOfColumns = columnType.length;

            for (int column = 0; column < numberOfColumns; column++) {
                columnBuilder.append(checkRowType(header, columnType, values, column));
                columnBuilder.append(addCommmaSeparator(column, numberOfColumns));
            }
        }
        return columnBuilder.toString();
    }

    /**
     * Removes all the empty lines in the kodeverk file. If the first column in a row has a value,
     * then it is a valid row.
     *
     * @param kodeverkList the kodeverklist to filter
     * @return a list with with only valid rows
     */
    public String[][] removeEmptyRowsInKodeverk(String[][] kodeverkList) {
        String[][] kodeverkListWithoutEmptyLines = new String[kodeverkList.length - emptyRowCounter(kodeverkList)][kodeverkList[0].length];

        for (int row = 0; row < kodeverkListWithoutEmptyLines.length; row++) {
            if (!kodeverkList[row][0].equals("")) {
                kodeverkListWithoutEmptyLines[row] = kodeverkList[row];
            }
        }

        return kodeverkListWithoutEmptyLines;
    }

    private int emptyRowCounter(String[][] kodeverkList) {
        int emptyRowsCounter = 0;

        for (String[] kodeverkRow : kodeverkList) {
            if (kodeverkRow[0].equals("") || kodeverkRow[0].equals(" ")) {
                emptyRowsCounter++;
            }
        }
        return emptyRowsCounter;
    }

    private String checkRowType(String[] header, String[] columnType, String[] values, int column) {
        if (!isEmpty(values[column]) && isColumnADate(columnType, column)) {
            return convertDateString(StringUtils.trim(values[column]));
        } else if (!isEmpty(values[column]) && isColumnATimestamp(columnType, column)) {
            return convertTimestampString(StringUtils.trim(values[column]));
        } else if (isEmpty(columnType[column]) || isColumnOfTypeDecode(header, column) || columnType[column].charAt(0) == TEXT_COLUMN) {
            return "\"".concat(values[column]).concat("\"");
        } else {
            return values[column];
        }
    }

    private String getColumnAtRow(int row, String[][] kodeverklList) {
        StringBuilder columnBuilder = new StringBuilder();
        int numberOfColumns = kodeverklList[0].length;

        for (int column = 0; column < numberOfColumns; column++) {
            columnBuilder.append(kodeverklList[row][column]);
            columnBuilder.append(addCommmaSeparator(column, numberOfColumns));
        }

        return columnBuilder.toString();
    }

    private boolean isColumnOfTypeDecode(String[] columnType, int column) {
        return columnType[column].equals(COLUMN_DECODE) || columnType[column].equals(COLUMN_DEKODE);
    }

    private boolean isRowColumnTypeOrHeader(int row) {
        return row == 0 || row == 1;
    }

    private boolean isColumnADate(String[] columnType, int column) {
        return isColumnOfType(columnType, column, DATE_COLUMN);
    }

    private boolean isColumnATimestamp(String[] columnType, int column) {
        return isColumnOfType(columnType, column, TIMESTAMP_COLUMN);
    }

    private boolean isColumnOfType(String[] columnType, int column, char type) {
        return !isEmpty(columnType[column])
                && columnType[column].charAt(0) == type;
    }
}
