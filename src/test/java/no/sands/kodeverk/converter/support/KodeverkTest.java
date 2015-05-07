package no.sands.kodeverk.converter.support;

import java.io.File;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import no.sands.kodeverk.converter.support.Characters.CharactersBuilder;
import no.sands.kodeverk.converter.support.Date.DateBuilder;
import no.sands.kodeverk.converter.support.Index.IndexBuilder;
import no.sands.kodeverk.converter.support.Timestamp.TimeStampBuilder;
import no.sands.kodeverk.utils.FileUtil;

/**
 * Unit tests for {@link no.sands.kodeverk.converter.support.Kodeverk}
 *
 * @author Øyvind Strømmen
 */
public class KodeverkTest {

    @BeforeClass
    public static void setUp() {
        ContentFactory.registerContent(DataType.TIMESTAMP, new TimeStampBuilder());
        ContentFactory.registerContent(DataType.DATE, new DateBuilder());
        ContentFactory.registerContent(DataType.INDEX, new IndexBuilder());
        ContentFactory.registerContent(DataType.CHARACTERS, new CharactersBuilder());
    }

    @Test
    public void shouldCreateACompleteKodeverk() {
        File file = new File("C:/data/kodeverk_v2.0/src/test/resources/testdata/K_A_KODEVERK.csv");
        List<String[]> strings = FileUtil.readCSVFile(file);
        new Kodeverk.KodeverkBuilder("name", strings.remove(0), strings.remove(0), strings).build();
    }

}