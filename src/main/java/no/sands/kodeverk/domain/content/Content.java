package no.sands.kodeverk.domain.content;

/**
 * Represents the contents of a {@link no.sands.kodeverk.domain.Column}
 *
 * @see ContentFactory
 * @see ContentBuilder
 *
 * @author �yvind Str�mmen
 */
public interface Content {

    /**
     * Get the content as a String
     *
     * @return the content as a String
     */
    String getContentAsString();
}
