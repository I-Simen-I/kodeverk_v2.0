package no.sands.kodeverk.helper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static no.sands.kodeverk.common.CommonVariables.*;
import static no.sands.kodeverk.utils.DateUtil.isDateValid;
import static no.sands.kodeverk.utils.DateUtil.isTimestampValid;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Simen Søhol
 */
public class CSVErrorHelper {

    /**
     * Checks if the  columns contains invalid data
     *
     * @param fileName     the kodeverk name
     * @param csvList      the kodeverk  list
     * @param validColumns number of valid columns
     * @param row          the row to check
     * @return true if row contains errors, otherwise false
     */
    public List<String> rowContainError(String fileName, List<String[]> csvList, int validColumns, int row) {
        List<String> errorList = new ArrayList<String>();
        String[] headerRow = csvList.get(CSV_HEADER_ROW);
        String[] valueRow = csvList.get(row);

        if (isBlank(valueRow[FIRST_COLUMN]) || valueRow[FIRST_COLUMN].equals(CSV_NULL_VALUE)) {
            errorList.add(format(SQL_FIRST_COLUMN_IS_EMPTY_ERROR_MESSAGE, fileName, (row + 1)));
        }

        for (int column = FIRST_COLUMN; column < validColumns; column++) {
            if (isBlank(valueRow[column]) || valueRow[column].equals(CSV_NULL_VALUE)) {
                validateThatMandatoryColumnsIsSet(errorList, fileName, headerRow[column], valueRow[FIRST_COLUMN]);
            } else {
                validateDate(errorList, fileName, headerRow[column], valueRow[column], valueRow[FIRST_COLUMN]);
                validateTimestamps(errorList, fileName, headerRow[column], valueRow[column], valueRow[FIRST_COLUMN]);
            }
        }
        return errorList;
    }

    private void validateThatMandatoryColumnsIsSet(List<String> errorList, String fileName, String headerRow, String kode) {
        if (headerRow.equals(COLUMN_GYLDIG)) {
            errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
        if (headerRow.equals(COLUMN_DATO_FOM)) {
            errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
        if (headerRow.equals(COLUMN_DATO_OPPRETTET)) {
            errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
        if (headerRow.equals(COLUMN_OPPRETTET_AV)) {
            errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
        if (headerRow.equals(COLUMN_DATO_ENDRET)) {
            errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
        if (headerRow.equals(COLUMN_ENDRET_AV)) {
            errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
    }

    private void validateTimestamps(List<String> errorList, String fileName, String headerRow, String columnValue, String kode) {
        if ((headerRow.equals(COLUMN_DATO_OPPRETTET) || headerRow.equals(COLUMN_DATO_ENDRET)) && !isTimestampValid(columnValue)) {
            errorList.add(format(SQL_WRONG_TIMESTAMP_FORMAT_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
    }

    private void validateDate(List<String> errorList, String fileName, String headerRow, String columnValue, String kode) {
        if ((headerRow.equals(COLUMN_DATO_FOM) || headerRow.equals(COLUMN_DATO_TOM)) && !isDateValid(columnValue)) {
            errorList.add(format(SQL_WRONG_DATE_FORMAT_ERROR_MESSAGE, fileName, headerRow.toUpperCase(), kode));
        }
    }
}