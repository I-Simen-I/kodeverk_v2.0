package no.sands.kodeverk.utils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class DateUtilTest {

    @Test
    public void testValidateTimestamp() {
        assertTrue(DateUtil.isTimestampValid("2015-01-10 10:00"));
        assertTrue(DateUtil.isTimestampValid("2015-12-10 23:00"));
        assertTrue(DateUtil.isTimestampValid("2015-01-10 10:59"));
        assertTrue(DateUtil.isTimestampValid("2016-02-29 23:59"));
    }

    @Test
    public void testInvalidateTimestamp() {
        assertFalse(DateUtil.isTimestampValid(""));
        assertFalse(DateUtil.isTimestampValid("2015-12-02"));
        assertFalse(DateUtil.isTimestampValid("2015-02-29 10:00"));
        assertFalse(DateUtil.isTimestampValid("2015-12-32 23:00"));
        assertFalse(DateUtil.isTimestampValid("2015-01-10 10:06:59.0"));
        assertFalse(DateUtil.isTimestampValid("2015-1d-10 10:60:59.0"));
        assertFalse(DateUtil.isTimestampValid("2015-1-1 10:60:59.0"));
        assertFalse(DateUtil.isTimestampValid(null));
    }

    @Test
    public void testValidateDate() {
        assertTrue(DateUtil.isDateValid("10.01.2015"));
        assertTrue(DateUtil.isDateValid("29.02.2016"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(DateUtil.isDateValid(""));
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
        assertFalse(DateUtil.isDateValid(null));
    }

    @Test
    public void shouldReturnTimestampAsStringInCorrectFormat() {
        String formatOne = "1900-01-15 23:00";
        String formatTwo = "1900/01/01 10:10";
        String formatThree = "28.01.1900 10:00";
        String formatFour = "1900.01.01 10:10";
        String formatFive = "8/14/14 14:00";
        String formatSix = "2014-09-19 12:00:06";

        assertThat(DateUtil.convertTimestampString(formatOne), is("1900-01-15 23:00"));
        assertThat(DateUtil.convertTimestampString(formatTwo), is("1900-01-01 10:10"));
        assertThat(DateUtil.convertTimestampString(formatThree), is("1900-01-28 10:00"));
        assertThat(DateUtil.convertTimestampString(formatFour), is("1900-01-01 10:10"));
        assertThat(DateUtil.convertTimestampString(formatFive), is("2014-08-14 14:00"));
        assertThat(DateUtil.convertTimestampString(formatSix), is("2014-09-19 12:00"));
    }

    @Test
    public void shouldReturnNullWhenTimestampIsInInvalidFormat() {
        String formatOne = "Winter is coming";
        String formatTwo = "01.28.1900 10:00";
        String formatThree = "01.01.2015";
        String formatFour = "1900/01/01 25:10";
        String formatFive = "1900-01-32 23:00";

        assertThat(DateUtil.convertTimestampString(null), is(nullValue()));
        assertThat(DateUtil.convertTimestampString(formatOne), is(nullValue()));
        assertThat(DateUtil.convertTimestampString(formatTwo), is(nullValue()));
        assertThat(DateUtil.convertTimestampString(formatThree), is(nullValue()));
        assertThat(DateUtil.convertTimestampString(formatFour), is(nullValue()));
        assertThat(DateUtil.convertTimestampString(formatFive), is(nullValue()));
    }

    @Test
    public void shouldReturnDateAsStringInCorrectFormat() {
        String formatOne = "1900-01-15";
        String formatTwo = "1900/01/01";
        String formatThree = "28.01.1900";
        String formatFour = "1900.01.01";
        String formatFive = "8/14/14";

        assertThat(DateUtil.convertDateString(formatOne), is("15.01.1900"));
        assertThat(DateUtil.convertDateString(formatTwo), is("01.01.1900"));
        assertThat(DateUtil.convertDateString(formatThree), is("28.01.1900"));
        assertThat(DateUtil.convertDateString(formatFour), is("01.01.1900"));
        assertThat(DateUtil.convertDateString(formatFive), is("14.08.2014"));
    }

    @Test
    public void shouldReturnNullWhenDateIsInInvalidFormat() {
        String formatOne = "Winter is coming";
        String formatTwo = "01.28.1900";
        String formatThree = "01.01.20d15";
        String formatFour = "1900/22/01";
        String formatFive = "1900-01-32 23:00";

        assertThat(DateUtil.convertDateString(null), is(nullValue()));
        assertThat(DateUtil.convertDateString(formatOne), is(nullValue()));
        assertThat(DateUtil.convertDateString(formatTwo), is(nullValue()));
        assertThat(DateUtil.convertDateString(formatThree), is(nullValue()));
        assertThat(DateUtil.convertDateString(formatFour), is(nullValue()));
        assertThat(DateUtil.convertDateString(formatFive), is(nullValue()));
    }
}