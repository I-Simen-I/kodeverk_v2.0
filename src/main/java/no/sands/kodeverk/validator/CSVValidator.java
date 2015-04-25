package no.sands.kodeverk.validator;

import static no.sands.kodeverk.common.CommonVariables.FIRS_KODEVERK_ROW_WITH_VALUES;
import static no.sands.kodeverk.utils.FileUtil.getFileName;
import static no.sands.kodeverk.utils.FileUtil.getFilesInFolder;
import static no.sands.kodeverk.utils.FileUtil.getNumberOfValidInsertValues;
import static no.sands.kodeverk.utils.FileUtil.readCSVFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import no.sands.kodeverk.converter.CSVToSQLConverter;
import no.sands.kodeverk.helper.CSVErrorHelper;

/**
 * @author Simen Søhol
 */
public class CSVValidator {
    private static final Logger LOGGER = Logger.getLogger(CSVToSQLConverter.class);

    private CSVErrorHelper csvErrorHelper = new CSVErrorHelper();

    /**
     * Loops trugh all the CSV-files in the given folder.
     *
     * @return number of errors
     * @throws IOException
     */
    public int validateCSV(String kodeverkFilePath) throws IOException {
        List<String> errorList = new ArrayList<>();

        for (File file : getFilesInFolder(kodeverkFilePath)) {
            List<String[]> csvLines = readCSVFile(file);

            System.out.println(getFileName(file));
            for (int i = FIRS_KODEVERK_ROW_WITH_VALUES; i < csvLines.size(); i++) {
                errorList.addAll(rowContainError(getFileName(file), csvLines, i));
            }
        }
        for (String error : errorList) {
            LOGGER.log(Level.ERROR, error);
        }

        return errorList.size();
    }

    private List<String> rowContainError(String fileName, List<String[]> csvList, int row) {
        int numberOfValidColumns = getNumberOfValidInsertValues(csvList);

        return csvErrorHelper.rowContainError(fileName, csvList, numberOfValidColumns, row);
    }
}
