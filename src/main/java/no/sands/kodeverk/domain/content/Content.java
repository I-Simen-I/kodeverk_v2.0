package no.sands.kodeverk.domain.content;

/**
 * Represents the contents of a {@link no.sands.kodeverk.domain.Column}
 *
 * @see ContentFactory
 * @see ContentBuilder
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
