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
        String[] columnTypeRow = csvList.get(CSV_COLUMN_TYPE_ROW);
        String[] valueRow = csvList.get(row);

        if (isBlank(valueRow[FIRST_COLUMN])) {
            errorList.add(format(SQL_FIRST_COLUMN_IS_EMPTY_ERROR_MESSAGE, fileName, (row + 1)));
        }

        for (int column = FIRST_COLUMN; column < validColumns; column++) {
            if (isBlank(valueRow[column])) {
                if (headerRow[column].equals(COLUMN_DEKODE) || headerRow[column].equals(COLUMN_DECODE)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_DEKODE.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
                if (headerRow[column].equals(COLUMN_GYLDIG)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_GYLDIG.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
                if (headerRow[column].equals(COLUMN_DATO_FOM)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_DATO_FOM.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
                if (headerRow[column].equals(COLUMN_DATO_OPPRETTET)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_DATO_OPPRETTET.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
                if (headerRow[column].equals(COLUMN_OPPRETTET_AV)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_OPPRETTET_AV.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
                if (headerRow[column].equals(COLUMN_DATO_ENDRET)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_DATO_ENDRET.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
                if (headerRow[column].equals(COLUMN_ENDRET_AV)) {
                    errorList.add(format(SQL_NO_VALUE_ERROR_MESSAGE, fileName, COLUMN_ENDRET_AV.toUpperCase(), valueRow[FIRST_COLUMN]));
                }
            } else {
                if (columnTypeRow[column].charAt(0) == DATE_COLUMN && !isDateValid(valueRow[column])) {
                    errorList.add(format(SQL_WRONG_DATE_FORMAT_ERROR_MESSAGE, fileName, valueRow[FIRST_COLUMN]));
                }

                if (columnTypeRow[column].charAt(0) == TIMESTAMP_COLUMN && !isTimestampValid(valueRow[column])) {
                    errorList.add(format(SQL_WRONG_TIMESTAMP_FORMAT_ERROR_MESSAGE, fileName, valueRow[FIRST_COLUMN]));
                }
            }
        }
        return errorList;
    }
}