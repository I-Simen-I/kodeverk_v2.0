package no.sands.kodeverk.domain.content;

/**
 * A generic builder interface meant to be used by the {@link ContentFactory}. Allows for generic creation of {@link Content}
 *
 * @see ContentFactory
 * @see Content
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
     * Build {@link Content} of a the given type. If rawContent is provided, an attempt to convert it will be made.
     *
     * @return a Content instance
     */
    public C build();
}
