package no.sands.kodeverk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Simen Søhol
 */
public class DateUtil {
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int DATE_LENGTH = 10;
    private static final int TIMESTAMP_LENGTH = 21;

    /**
     * Checks if date format is yyyy-MM-dd hh:mm:ss.SSS
     *
     * @param date the date to validate
     * @return true if date is valid, otherwise false
     */
    public static boolean isTimestampValid(String date) {
        return date.length() == TIMESTAMP_LENGTH && validate(date, TIMESTAMP_FORMAT);
    }

    /**
     * Checks if date format is yyyy-MM-dd
     *
     * @param date the date to validate
     * @return true if date is valid, otherwise false
     */
    public static boolean isDateValid(String date) {
        return date.length() == DATE_LENGTH && validate(date, DATE_FORMAT);
    }

    private static boolean validate(String date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setLenient(false);

        try {
            format.parse(date);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
