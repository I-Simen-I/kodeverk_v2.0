package no.sands.kodeverk.converter.support;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Øyvind Strømmen
 */
public class ContentFactory {

    private static Map<DataType, Content> registeredContent = new EnumMap<>(DataType.class);

    public static Content createContent(DataType targetDataType, String rawContent) {


        Content content = registeredContent.get(targetDataType).withRawContent(rawContent);

        return null;
    }
}
