package no.sands.kodeverk.converter.support;

import org.junit.Test;

import no.sands.kodeverk.converter.support.Timestamp.TimeStampBuilder;

public class ContentFactoryTest {

    static {
        ContentFactory.registerContent(DataType.TIMESTAMP, new TimeStampBuilder());
    }

    @Test
    public void testTest() {
        ContentFactory.createContent(DataType.TIMESTAMP, "");
    }

}