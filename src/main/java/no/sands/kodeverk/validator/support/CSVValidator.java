package no.sands.kodeverk.validator.support;

import static no.sands.kodeverk.utils.FileUtil.getFileName;
import static no.sands.kodeverk.utils.FileUtil.getNumberOfValidInsertValues;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.helper.CSVErrorHelper;
import no.sands.kodeverk.utils.FileUtil;
import no.sands.kodeverk.validator.client.Validator;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class CSVValidator implements Validator {

    private CSVErrorHelper csvErrorHelper = new CSVErrorHelper();

    @Override
    public List<KodeverkError> validate(String filePath) {

        List<KodeverkError> errorList = new ArrayList<>();

        for (File file : FileUtil.getFilesInFolder(filePath)) {

            List<String[]> lines = FileUtil.readCSVFile(file);

            for (int i = CommonVariables.FIRST_KODEVERK_ROW_WITH_VALUES; i < lines.size(); i++) {

                errorList.addAll(rowContainError(getFileName(file), lines, i));

            }
        }

        return errorList;
    }

    private List<KodeverkError> rowContainError(String fileName, List<String[]> csvList, int row) {

        int numberOfValidColumns = getNumberOfValidInsertValues(csvList);

        return csvErrorHelper.rowContainError(fileName, csvList, numberOfValidColumns, row);
    }
}
