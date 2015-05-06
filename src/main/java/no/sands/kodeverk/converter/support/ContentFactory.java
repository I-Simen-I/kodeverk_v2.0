package no.sands.kodeverk.converter.support;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Øyvind Strømmen
 */
public class ContentFactory {

    private static Map<DataType, ContentBuilder> registeredContent = new EnumMap<>(DataType.class);

    public static void registerContent(DataType dataType, ContentBuilder contentBuilder) {
        registeredContent.put(dataType, contentBuilder);
    }

    public static Content createContent(DataType targetDataType, String rawContent) {
        return registeredContent.get(targetDataType).rawContent(rawContent).build();
    }
}
