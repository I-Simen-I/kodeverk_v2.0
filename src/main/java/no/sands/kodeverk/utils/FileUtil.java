package no.sands.kodeverk.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static no.sands.kodeverk.enums.FileType.CSV;

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
        return getFilesInFolderOfGivenType(folderPath, CSV.getFiletype());
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
}
