package no.sands.kodeverk.converter.support;

/**
 * @author �yvind Str�mmen
 */
public interface ContentBuilder<C extends Content> {

    public ContentBuilder rawContent(String rawContent);

    public C build();

}
