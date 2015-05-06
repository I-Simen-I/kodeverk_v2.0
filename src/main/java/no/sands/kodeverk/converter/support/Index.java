package no.sands.kodeverk.converter.support;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * @author Øyvind Strømmen
 */
public class Index implements Content {

    private String content;

    public Content withRawContent(String rawContent) throws KodeverkInvalidContentException {
        if (rawContent != null && StringUtils.isNumeric(rawContent)) {
            content = rawContent;
            return this;
        }
        throw new KodeverkInvalidContentException("Expected '" + rawContent + "' to be a numerical");
    }

    @Override
    public String getContentAsString() {
        return content;
    }
}
