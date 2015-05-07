package no.sands.kodeverk.converter.support;

/**
 * Represents the contents of a {@link no.sands.kodeverk.converter.support.Column}
 *
 * @see no.sands.kodeverk.converter.support.ContentFactory
 * @see no.sands.kodeverk.converter.support.ContentBuilder
 *
 * @author Øyvind Strømmen
 */
public interface Content {

    /**
     * Get the content as a String
     *
     * @return the content as a String
     */
    String getContentAsString();
}
