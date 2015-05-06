package no.sands.kodeverk.converter.support;

/**
 * @author Øyvind Strømmen
 */
public interface ContentBuilder<C extends Content> {

    public ContentBuilder rawContent(String rawContent);

    public C build();

}
