package no.sands.kodeverk.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

import static no.sands.kodeverk.common.CommonVariables.CSV_FILE;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * @author Simen Søhol
 */
public class FileUtil {

    /**
     * Returns all csv files in a given folder
     *
     * @param folderPath the path to the folder where the kodeverkfiles are stored
     * @return list of files
     */
    public static List<File> getFilesInFolder(String folderPath) {
        return getFilesInFolderOfGivenType(folderPath, CSV_FILE);
    }

    /**
     * Returns all files of given file type in a given folder
     *
     * @param folderPath the path to the folder where the kodeverkfiles are stored
     * @param fileType   the type of file to find
     * @return list of files
     */
    public static List<File> getFilesInFolderOfGivenType(String folderPath, final String fileType) {
        File folder = new File(folderPath);
        List<File> filesInFolder = new ArrayList<File>();

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(fileType);
            }
        };

        Collections.addAll(filesInFolder, folder.listFiles(filter));

        return filesInFolder;
    }

    /**
     * Reads a given csv file and returns a list that contains each line
     *
     * @param kodeverkFile the file to read
     * @return list that contains each line as a String[]
     */
    public static List<String[]> readCSVFile(File kodeverkFile) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(kodeverkFile));

        return reader.readAll();
    }

    /**
     * Get the name on the file without the extension
     *
     * @param file the file to get the name from
     * @return the name of  the file
     */
    public static String getFileName(File file) {
        int fileTypeIndex = file.getName().indexOf(".");

        return file.getName().substring(0, fileTypeIndex);
    }

    /**
     * Returns the number of valid rows that can be used in sql a insert statement
     *
     * @param csvList the list to check
     * @return number of valid rows
     */
    public static int getNumberOfValidInsertValues(List<String[]> csvList) {
        String[] columnTypes = csvList.get(1);
        int validInsertValues = 0;

        for (String column : columnTypes) {
            if (column != null && !isBlank(column)) {
                validInsertValues++;
            }
        }

        return validInsertValues;
    }
}
