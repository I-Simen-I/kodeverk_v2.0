package no.sands.kodeverk.utils;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.Set;

/**
 * @author Simen Søhol
 * @author Øyvind Strømmen
 */
public class DateUtil {
    private static final String NORWEGIAN_LOCALE = "nb";
    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    private static final String VALID_DATE_FORMAT = "yyyy-MM-dd";
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
     * Determine if a timestamp string is formatted as yyyy-MM-dd hh:mm
     *
     * @param date the timestamp to validate
     * @return true if timestamp is valid, false otherwise
     */
    public static boolean isTimestampValid(String date) {
        return date != null && date.length() == TIMESTAMP_LENGTH && validate(date, DEFAULT_TIMESTAMP_FORMAT);
    }

    /**
     * Determine if a date string is formatted as either yyyy-MM-dd or dd.MM.yyyy
     *
     * @param date the date to validate
     * @return true if date is valid, false otherwise
     */
    public static boolean isDateValid(String date) {
        return date != null && date.length() == DATE_LENGTH && validate(date, DEFAULT_DATE_FORMAT, VALID_DATE_FORMAT);
    }

    private static boolean validate(String date, String ... dateFormats) {
        if (StringUtils.isNotBlank(date)) {
            for (String dateFormat : dateFormats) {
                try {
                    DateTimeFormat.forPattern(dateFormat).parseDateTime(date);
                    return true;
                } catch (UnsupportedOperationException | IllegalArgumentException ignore) {}
            }
        }
        return false;
    }

    private static String convertToFormat(String date, String defaultFormat, Set<String> supportedFormats) {
        if (date == null) {
            return null;
        }
        String correctlyFormattedDateString = null;

        for (String format : supportedFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withLocale(Locale.forLanguageTag(NORWEGIAN_LOCALE));
                correctlyFormattedDateString = formatter.parseDateTime(date).toString(defaultFormat);
            } catch (UnsupportedOperationException | IllegalArgumentException ignored) {}
        }
        return correctlyFormattedDateString;
    }

    /**
     * Attempt to convert any given String to a default timestamp format (yyyy-MM-dd HH:mm) from a set of supported formats
     * <br>
     * Supported formats are as follows:
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
     * Attempt to convert any given String to a default date format (dd.MM.yyyy) from a set of supported formats
     * <br>
     * Supported formats are as follows:
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
