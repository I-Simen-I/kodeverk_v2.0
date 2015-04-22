package no.sands.kodeverk.utils;

import java.util.Locale;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class DateUtil {
    private static final String NORWEGIAN_LOCALE = "nb";
    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final int DATE_LENGTH = 10;
    private static final int TIMESTAMP_LENGTH = 16;

    private static final ImmutableSet<String> SUPPORTED_TIMESTAMP_FORMATS = ImmutableSet.of(
            DEFAULT_TIMESTAMP_FORMAT,
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm",
            "dd.MM.yyyy HH:mm",
            "yyyy.dd.MM HH:mm",
            "MM/dd/yy HH:mm"
    );

    private static final ImmutableSet<String> SUPPORTED_DATE_FORMATS = ImmutableSet.of(
            DEFAULT_DATE_FORMAT,
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd.MM.yyyy",
            "yyyy.dd.MM",
            "MM/dd/yy"
    );

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
     * Checks if date format is yyyy-MM-dd
     *
     * @param date the date to validate
     * @return true if date is valid, otherwise false
     */
    public static boolean isDateValid(String date) {
        return date != null && date.length() == DATE_LENGTH && validate(date, DEFAULT_DATE_FORMAT);
    }

    private static boolean validate(String date, String dateFormat) {
        try {
            DateTimeFormat.forPattern(dateFormat).parseDateTime(date);
        } catch (UnsupportedOperationException | IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    private static String convertToFormat(String date, String defaultFormat, Set<String> suportedFormats) {
        if (date == null) {
            return null;
        }
        String correctlyFormattedDateString = null;

        for (String format : suportedFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withLocale(Locale.forLanguageTag(NORWEGIAN_LOCALE));
                correctlyFormattedDateString = formatter.parseDateTime(date).toString(defaultFormat);
            } catch (UnsupportedOperationException | IllegalArgumentException ignored) {}
        }
        return correctlyFormattedDateString;
    }

    /**
     * Attempt to convert any given String to a default timestamp format (yyyy-MM-dd HH:mm) from a set of supported formatts
     * <br>
     * Supported formatts are as follows:
     * <ul>
     * <li>yyyy-MM-dd HH:mm:ss</li>
     * <li>yyyy/MM/dd HH:mm</li>
     * <li>dd.MM.yyyy HH:mm</li>
     * <li>yyyy.dd.MM HH:mm</li>
     * <li>MM/dd/yy HH:mm</li>
     * <li>yyyy-MM-dd HH:mm</li>
     * </ul>
     * If the supplied String isn't formatted as any of these - conversion is impossible
     *
     * @param date the date to convert
     * @return a String in the default format or null if conversion is impossible or null is passed as method argument
     */
    public static String convertTimestampString(String date) {
        return convertToFormat(date, DEFAULT_TIMESTAMP_FORMAT, SUPPORTED_TIMESTAMP_FORMATS);
    }

    /**
     * Attempt to convert any given String to a default date format (yyyy-MM-dd) from a set of supported formatts
     * <br>
     * Supported formatts are as follows:
     * <ul>
     * <li>yyyy-MM-dd</li>
     * <li>yyyy/MM/dd</li>
     * <li>dd.MM.yyyy</li>
     * <li>yyyy.dd.MM</li>
     * <li>MM/dd/yy</li>
     * </ul>
     * If the supplied String isn't formatted as any of these - conversion is impossible
     *
     * @param date the date to convert
     * @return a String in the default format or null if conversion is impossible or null is passed as method argument
     */
    public static String convertDateString(String date) {
        return convertToFormat(date, DEFAULT_DATE_FORMAT, SUPPORTED_DATE_FORMATS);
    }
}
