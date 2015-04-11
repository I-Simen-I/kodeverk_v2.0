package no.sands.kodeverk.utils;

import org.junit.Test;

import static no.sands.kodeverk.common.CommonVariables.TEST_FILE_PATH;
import static no.sands.kodeverk.enums.FileType.XLS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Simen Søhol
 */
public class FileUtilTest {

    @Test
    public void testGetFilesInFolder() throws Exception {
        assertThat(FileUtil.getFilesInFolder(TEST_FILE_PATH), hasSize(1));
    }

    @Test
    public void testGetFilesInFolderOfGivenType() throws Exception {
        assertThat(FileUtil.getFilesInFolderOfGivenType(TEST_FILE_PATH, XLS.getFiletype()), hasSize(1));
    }
}