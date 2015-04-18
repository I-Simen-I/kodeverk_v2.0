package no.sands.kodeverk.utils;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static no.sands.kodeverk.common.CommonVariables.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * @author Simen S�hol
 */
public class FileUtilTest {

    @Test
    public void testGetFilesInFolder() throws Exception {
        assertThat(FileUtil.getFilesInFolder(TEST_FILE_PATH), hasSize(1));
    }

    @Test
    public void testGetFilesInFolderOfGivenType() throws Exception {
        assertThat(FileUtil.getFilesInFolderOfGivenType(TEST_FILE_PATH, XLS_FILE), hasSize(1));
    }

    @Test
    public void testReadFile() throws Exception {
        List<File> kodeverkList = FileUtil.getFilesInFolder(TEST_FILE_PATH);
        assertThat(kodeverkList, hasSize(1));

        File kodeverk = kodeverkList.get(0);

        List<String[]> csvList = FileUtil.readCSVFile(kodeverk);
        assertThat(csvList, hasSize(5));
        assertThat(csvList.get(0).length, is(10));
    }

    @Test
    public void testGetNumberOfValidInsertValues() {
        String[] header = {"a", "b", "c", "d"};
        String[] columnType = {"c", "c", "d"};
        List<String[]> csvList = Arrays.asList(header, columnType);

        assertThat(FileUtil.getNumberOfValidInsertValues(csvList), is(3));
    }

    @Test
    public void testGetFileName() {
        File file = new File(TEST_FILE_1);

        assertThat(FileUtil.getFileName(file), is("kodeverk_to_import"));
    }
}