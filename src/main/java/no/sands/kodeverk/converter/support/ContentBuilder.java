package no.sands.kodeverk.converter.support;

/**
 * A generic builder interface meant to be used by the {@link no.sands.kodeverk.converter.support.ContentFactory}. Allows for generic creation of {@link no.sands.kodeverk.converter.support.Content}
 *
 * @see no.sands.kodeverk.converter.support.ContentFactory
 * @see no.sands.kodeverk.converter.support.Content
 *
 * @author Øyvind Strømmen
 */
public interface ContentBuilder<C extends Content> {

    /**
     * Supply this instance with rawContent in String form. An attempt to convert the rawContent to appropriate content will be made when calling {@link ContentBuilder#build()}.
     *
     * @param rawContent the rawContent
     * @return an instance of this ContentBuilder
     */
    public ContentBuilder rawContent(String rawContent);

    /**
     * Build {@link no.sands.kodeverk.converter.support.Content} of a the given type. If rawContent is provided, an attempt to convert it will be made.
     *
     * @return a Content instance
     */
    public C build();
}
