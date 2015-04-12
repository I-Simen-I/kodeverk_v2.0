package no.sands.kodeverk.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Simen S�hol
 */
public class DateUtilTest {

    @Test
    public void testValidateTimestamp() {
        assertTrue(DateUtil.isTimestampValid("2015-01-10 10:00:00.0"));
        assertTrue(DateUtil.isTimestampValid("2015-12-10 23:00:00.0"));
        assertTrue(DateUtil.isTimestampValid("2015-01-10 10:59:59.0"));
        assertTrue(DateUtil.isTimestampValid("2016-02-29 23:59:58.0"));
    }

    @Test
    public void testInvalidateTimestamp() {
        assertFalse(DateUtil.isTimestampValid("2015-12-02"));
        assertFalse(DateUtil.isTimestampValid("2015-02-29 10:00:00.0"));
        assertFalse(DateUtil.isTimestampValid("2015-12-32 23:00:00.0"));
        assertFalse(DateUtil.isTimestampValid("2015-01-10 10:60:59.0"));
        assertFalse(DateUtil.isTimestampValid("2015-1d-10 10:60:59.0"));
        assertFalse(DateUtil.isTimestampValid("2015-1-1 10:60:59.0"));
    }

    @Test
    public void testValidateDate() {
        assertTrue(DateUtil.isDateValid("2015-01-10"));
        assertTrue(DateUtil.isDateValid("2016-02-29"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(DateUtil.isDateValid("2015-01-10 10:00:00.000"));
        assertFalse(DateUtil.isDateValid("2015--01-10"));
        assertFalse(DateUtil.isDateValid("2015-13-10"));
        assertFalse(DateUtil.isDateValid("2015-12-32"));
        assertFalse(DateUtil.isDateValid("-2015-12-32"));
        assertFalse(DateUtil.isDateValid("s2015-12-32"));
        assertFalse(DateUtil.isDateValid("2015-02-29"));
        assertFalse(DateUtil.isDateValid("2015-32-26"));
        assertFalse(DateUtil.isDateValid("2015-1-26"));
        assertFalse(DateUtil.isDateValid("2015-01-1"));
    }
}