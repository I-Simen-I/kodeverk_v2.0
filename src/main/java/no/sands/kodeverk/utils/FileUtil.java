package no.sands.kodeverk.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Søhol
 */
public class FileUtil {

    /**
     * Returns all csv files in a given folder
     *
     * @param folderPath the path to the folder where the kodeverkfiles are stored
     * @return list of file names
     */
    public static List<String> getFilesInFolder(String folderPath) {
        return getFilesInFolderOfGivenType(folderPath, ".csv");
    }

    /**
     * Returns all files of given file type in a given folder
     *
     * @param folderPath the path to the folder where the kodeverkfiles are stored
     * @param fileType   the type of file to find
     * @return list of file names
     */
    public static List<String> getFilesInFolderOfGivenType(String folderPath, final String fileType) {
        File folder = new File(folderPath);
        List<String> filesInFolder = new ArrayList<String>();

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(fileType);
            }
        };

        for (File file : folder.listFiles(filter)) {
            filesInFolder.add(file.getName());
        }

        return filesInFolder;
    }
}
