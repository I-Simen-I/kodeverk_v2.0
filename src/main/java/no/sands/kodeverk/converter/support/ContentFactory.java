package no.sands.kodeverk.converter.support;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory in charge of creating {@link no.sands.kodeverk.converter.support.Content} to be contained within {@link no.sands.kodeverk.converter.support.Column}'s.
 * Available content are contained within an internal registry populated from the outside.
 *
 * @author Øyvind Strømmen
 */
public class ContentFactory {

    private static Map<DataType, ContentBuilder> registeredContent = new EnumMap<>(DataType.class);

    /**
     * Register Content to the factory. Content must be registered here for it to be eligable for creation.
     *
     * @param dataType the {@link no.sands.kodeverk.converter.support.DataType} to register the Content with
     * @param contentBuilder a {@link no.sands.kodeverk.converter.support.ContentBuilder}
     */
    public static void registerContent(DataType dataType, ContentBuilder contentBuilder) {
        registeredContent.put(dataType, contentBuilder);
    }

    /**
     * Creates {@link no.sands.kodeverk.converter.support.Content} based on a given {@link no.sands.kodeverk.converter.support.DataType}. Only Content registered using
     * {@link no.sands.kodeverk.converter.support.ContentFactory#registerContent(DataType, ContentBuilder)} will be available for creation.
     *
     * @param targetDataType a {@link no.sands.kodeverk.converter.support.DataType}
     * @param rawContent the rawContent which will be supplied to created Content
     * @return the appropriate Content or {@code null} if no appropriate Content was found
     */
    public static Content createContent(DataType targetDataType, String rawContent) {
        if (targetDataType != null) {
            return registeredContent.get(targetDataType).rawContent(rawContent).build();
        }
        return null;
    }
}
