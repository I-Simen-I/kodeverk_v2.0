package no.sands.kodeverk.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class DateUtil {
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int DATE_LENGTH = 10;
    private static final int TIMESTAMP_LENGTH = 16;

    /**
     * Checks if date format is yyyy-MM-dd hh:mm
     *
     * @param date the date to validate
     * @return true if date is valid, otherwise false
     */
    public static boolean isTimestampValid(String date) {
        return date != null && date.length() == TIMESTAMP_LENGTH && validate(date, TIMESTAMP_FORMAT);
    }

    /**
     * Checks if date format is yyyy-MM-dd or if date is an empty String
     *
     * @param date the date to validate
     * @return true if date is valid, otherwise false
     */
    public static boolean isDateValid(String date) {
        return date != null && ((date.length() == DATE_LENGTH && validate(date, DATE_FORMAT)) | StringUtils.isEmpty(date));
    }

    private static boolean validate(String date, String dateFormat) {
        try {
            DateTimeFormat.forPattern(dateFormat).parseDateTime(date);
        } catch (UnsupportedOperationException ex) {
            return false;
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
