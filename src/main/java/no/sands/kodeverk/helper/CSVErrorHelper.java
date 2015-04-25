package no.sands.kodeverk.helper;

import static java.lang.String.format;

import static org.apache.commons.lang3.StringUtils.isBlank;

import static no.sands.kodeverk.common.CommonVariables.COLUMN_DATO_ENDRET;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_DATO_FOM;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_DATO_OPPRETTET;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_DATO_TOM;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_ENDRET_AV;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_GYLDIG;
import static no.sands.kodeverk.common.CommonVariables.COLUMN_OPPRETTET_AV;
import static no.sands.kodeverk.common.CommonVariables.CSV_HEADER_ROW;
import static no.sands.kodeverk.common.CommonVariables.CSV_NULL_VALUE;
import static no.sands.kodeverk.common.CommonVariables.CSV_VALID_GYLDIG_VALUE_0;
import static no.sands.kodeverk.common.CommonVariables.CSV_VALID_GYLDIG_VALUE_1;
import static no.sands.kodeverk.common.CommonVariables.FIRST_COLUMN;
import static no.sands.kodeverk.common.CommonVariables.SQL_FIRST_COLUMN_IS_EMPTY_ERROR_MESSAGE;
import static no.sands.kodeverk.common.CommonVariables.SQL_NO_VALUE_ERROR_MESSAGE;
import static no.sands.kodeverk.common.CommonVariables.SQL_WRONG_DATE_FORMAT_ERROR_MESSAGE;
import static no.sands.kodeverk.common.CommonVariables.SQL_WRONG_TIMESTAMP_FORMAT_ERROR_MESSAGE;
import static no.sands.kodeverk.common.CommonVariables.SQL_WRONG_VALUE_GYLDIG;
import static no.sands.kodeverk.utils.DateUtil.isDateValid;
import static no.sands.kodeverk.utils.DateUtil.isTimestampValid;

import java.util.ArrayList;
import java.util.List;

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
        List<String> errorList = new ArrayList<>();
        String[] headerRow = csvList.get(CSV_HEADER_ROW);
        String[] valueRow = csvList.get(row);

        if (isBlank(valueRow[FIRST_COLUMN])) {
            errorList.add(format(SQL_FIRST_COLUMN_IS_EMPTY_ERROR_MESSAGE, fileName, (row + 1)));
        }

        for (int column = FIRST_COLUMN; column < validColumns; column++) {
            if (isBlank(valueRow[column]) || valueRow[column].equals(CSV_NULL_VALUE)) {
                validateThatMandatoryColumnsIsSet(errorList, fileName, headerRow[column], valueRow[FIRST_COLUMN]);
            } else {
                validateDate(errorList, fileName, headerRow[column], valueRow[column], valueRow[FIRST_COLUMN]);
                validateTimestamps(errorList, fileName, headerRow[column], valueRow[column], valueRow[FIRST_COLUMN]);
                if (headerRow[column].equals(COLUMN_GYLDIG)) {
                    validateGyldigColumn(errorList, fileName, valueRow[column], valueRow[FIRST_COLUMN]);
                }
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

    private void validateGyldigColumn(List<String> errorList, String fileName, String columnValue, String kode) {
        if (!columnValue.equals(CSV_VALID_GYLDIG_VALUE_0) && !columnValue.equals(CSV_VALID_GYLDIG_VALUE_1)) {
            errorList.add(format(SQL_WRONG_VALUE_GYLDIG, fileName, COLUMN_GYLDIG.toUpperCase(), kode));
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