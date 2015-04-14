package no.sands.kodeverk.utils;

import java.util.Locale;

import com.google.common.collect.ImmutableSet;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class DateUtil {
    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int DATE_LENGTH = 10;
    private static final int TIMESTAMP_LENGTH = 16;

    private static final ImmutableSet<String> SUPPORTED_TIMESTAMP_FORMATS = ImmutableSet.of(DEFAULT_TIMESTAMP_FORMAT, "yyyy/MM/dd HH:mm", "dd.MM.yyyy HH:mm", "yyyy.dd.MM HH:mm");

    /**
     * Checks if date format is yyyy-MM-dd hh:mm
     *
     * @param date the date to validate
     * @return true if date is valid, otherwise false
     */
    public static boolean isTimestampValid(String date) {
        return date != null && date.length() == TIMESTAMP_LENGTH && validate(date, DEFAULT_TIMESTAMP_FORMAT);
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

    /**
     * Attempt to convert any given String to a default timestamp format (yyyy-MM-dd HH:mm) from a set of supported formatts
     * <br>
     * Supported formatts are as follows:
     *
     * <ul>
     * <li>yyyy/MM/dd HH:mm</li>
     * <li>dd.MM.yyyy HH:mm</li>
     * <li>yyyy.dd.MM HH:mm</li>
     * <li>yyyy-MM-dd HH:mm</li>
     * </ul>
     *
     * If the supplied String isn't formatted as any of these - conversion is impossible
     *
     * @param date the date to convert
     * @return a String in the default format or null if conversion is impossible or null is passed as method argument
     */
    public static String convertDateString(String date) {
        if (date == null) {
            return null;
        }
        String correctlyFormattedDateString = null;

        for (String format : SUPPORTED_TIMESTAMP_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withLocale(Locale.forLanguageTag("nb"));
                correctlyFormattedDateString = formatter.parseDateTime(date).toString(DEFAULT_TIMESTAMP_FORMAT);
            } catch (UnsupportedOperationException ignored) {
            } catch (IllegalArgumentException ignored) {
            }
        }
        return correctlyFormattedDateString;
    }
}
