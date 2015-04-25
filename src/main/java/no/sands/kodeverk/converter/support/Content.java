package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author Øyvind Strømmen
 */
public interface Content {

    Content withRawContent(String rawContent) throws KodeverkInvalidContentException;

    String getContentAsString();
}
