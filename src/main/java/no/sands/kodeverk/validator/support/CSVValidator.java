package no.sands.kodeverk.validator.support;

import static no.sands.kodeverk.utils.FileUtil.getNumberOfValidInsertValues;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import no.sands.kodeverk.helper.CSVErrorHelper;
import no.sands.kodeverk.utils.FileUtil;
import no.sands.kodeverk.validator.client.Validator;

/**
 * @author Simen S�hol
 * @author �yvind Str�mmen
 */
public class CSVValidator implements Validator {

    private CSVErrorHelper csvErrorHelper = new CSVErrorHelper();

    @Override
    public List<KodeverkError> validate(String filePath) {

        List<KodeverkError> errorList = new ArrayList<>();

        for (File file : FileUtil.getFilesInFolder(filePath)) {
//            new Kodeverk().withRawValues(FileUtil.readCSVFile(file));
        }
        return errorList;
    }

    private List<KodeverkError> rowContainError(String fileName, List<String[]> csvList, int row) {

        int numberOfValidColumns = getNumberOfValidInsertValues(csvList);

        return csvErrorHelper.rowContainError(fileName, csvList, numberOfValidColumns, row);
    }
}
