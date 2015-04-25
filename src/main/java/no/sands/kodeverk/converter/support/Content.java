package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author �yvind Str�mmen
 */
public interface Content {

    Content withRawContent(String rawContent) throws KodeverkInvalidContentException;

    String getContentAsString();
}
