package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author �yvind Str�mmen
 */
public class Characters implements Content {

    private String content;

    @Override
    public Content withRawContent(String rawContent) throws KodeverkInvalidContentException {
        content = rawContent;
        return this;
    }

    @Override
    public String getContentAsString() {
        return content;
    }
}
